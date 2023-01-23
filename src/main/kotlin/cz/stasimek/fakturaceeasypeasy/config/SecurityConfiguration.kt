package cz.stasimek.fakturaceeasypeasy.config

import cz.stasimek.fakturaceeasypeasy.config.user.AppOAuth2User
import cz.stasimek.fakturaceeasypeasy.config.user.AppOidcUser
import cz.stasimek.fakturaceeasypeasy.enumeration.*
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException
import cz.stasimek.fakturaceeasypeasy.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.Customizer
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer
import org.springframework.security.config.annotation.web.configurers.oauth2.client.OAuth2LoginConfigurer
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction
import org.springframework.security.oauth2.core.oidc.user.OidcUser
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler
import org.springframework.security.web.context.SecurityContextHolderFilter
import org.springframework.security.web.csrf.CookieCsrfTokenRepository
import org.springframework.web.reactive.function.client.WebClient
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class SecurityConfiguration(
    private val userService: UserService
) {

    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            // URLs available without login.
            .authorizeRequests(
                Customizer { a /*: ExpressionInterceptUrlRegistry*/ ->
                    a.antMatchers("/", "/api/login/error", "/static/**", "/api/**", "/**").permitAll()
                        .anyRequest().authenticated()
                }
            )
            // On AJAX request 401 instead of the default redirecting to a login page.
            .exceptionHandling { e: ExceptionHandlingConfigurer<HttpSecurity> ->
                e.authenticationEntryPoint(
                    HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)
                )
            }
            // Spring Security has built in support for a /logout endpoint
            // which clear the session and invalidate the cookie.
            .logout { l: LogoutConfigurer<HttpSecurity> ->
                l.logoutSuccessUrl("/").permitAll()
            }
            // Create cookie XSRF-TOKEN.
            .csrf { c: CsrfConfigurer<HttpSecurity> -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) }
            // On login save "Referer" HTTP header to session as "login.redirectUri".
            .addFilterBefore(
                { request: ServletRequest, response: ServletResponse, chain: FilterChain ->
                    val r = request as HttpServletRequest
                    val uri = r.requestURI
                    if (uri != null && uri.startsWith("/oauth2/authorization/")) {
                        r.session.setAttribute("login.redirectUri", r.getHeader("Referer"))
                    }
                    chain.doFilter(request, response)
                },
                SecurityContextHolderFilter::class.java
            )
            .oauth2Login { o: OAuth2LoginConfigurer<HttpSecurity> ->
                // On failure set session "login.errorMessage", then redirect to login page.
                o.failureHandler { request: HttpServletRequest, response: HttpServletResponse, exception: AuthenticationException ->
                    request.session.setAttribute("login.errorMessage", exception.message)
                    val handler: AuthenticationFailureHandler = SimpleUrlAuthenticationFailureHandler("/")
                    handler.onAuthenticationFailure(request, response, exception)
                }
                // On success redirect user to original URL stored in session "login.redirectUri".
                o.successHandler { request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication ->
                    val redirectUri = request.session.getAttribute("login.redirectUri") as String?
                    if (redirectUri != null) {
                        request.session.removeAttribute("login.redirectUri")
                        val handler = SimpleUrlAuthenticationSuccessHandler(redirectUri)
                        handler.onAuthenticationSuccess(request, response, authentication)
                    }
                }
            }
            .build()
    }

    @Bean
    fun rest(
        clients: ClientRegistrationRepository, authz: OAuth2AuthorizedClientRepository
    ): WebClient {
        return WebClient
            .builder()
            .filter(ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz))
            .build()
    }

    /**
     * This is triggered after GitHub "social" user logged in.
     */
    @Bean
    fun oauth2UserService(rest: WebClient): OAuth2UserService<OAuth2UserRequest, OAuth2User> {
        val delegate = DefaultOAuth2UserService()
        return OAuth2UserService { request: OAuth2UserRequest -> handleUserLogin(request, delegate/*, rest*/) }
    }

    /**
     * This is triggered after Google "social" user logged in.
     */
    @Bean
    @Suppress("UNCHECKED_CAST")
    fun oidcUserService(rest: WebClient): OAuth2UserService<OidcUserRequest, OidcUser> {
        val delegate = OidcUserService()
        return OAuth2UserService { request: OidcUserRequest ->
            val user = handleUserLogin(
                request,
                // @Suppress("UNCHECKED_CAST") is because of this
                delegate as OAuth2UserService<OAuth2UserRequest, OAuth2User>
                /*, rest*/
            )
            if (user is OidcUser) {
                return@OAuth2UserService user
            }
            throw ApplicationException(
                String.format("Instance of OidcUser expected, get %s.", user.javaClass.simpleName)
            )
        }
    }

    private fun handleUserLogin(
        request: OAuth2UserRequest,
        delegate: OAuth2UserService<OAuth2UserRequest, OAuth2User>
        /*, rest: WebClient*/
    ): OAuth2User {
        val user = delegate.loadUser(request)
        val provider: Provider = Provider.Companion.valueOfCaseInsensitive(
            request.clientRegistration.registrationId
        )
        // Check that user is member of GitHub's Spring Team
        //if (provider == Provider.GITHUB) {
        //	return checkUserIsMemberOfSpringTeam(request, user, rest);
        //}
        val appUser = userService.createUserIfNotExist(provider, user)
        if (user is OidcUser) {
            return AppOidcUser(user, appUser)
        } else {
            return AppOAuth2User(user, appUser)
        }
    }

    //	private OAuth2User checkUserIsMemberOfSpringTeam(
    //			OAuth2UserRequest request, OAuth2User user, WebClient rest
    //	) {
    //		OAuth2AuthorizedClient client = new OAuth2AuthorizedClient(
    //				request.getClientRegistration(), user.getName(), request.getAccessToken()
    //		);
    //		String url = user.getAttribute("organizations_url");
    //		List<Map<String, Object>> orgs = rest
    //				.get().uri(url)
    //				.attributes(oauth2AuthorizedClient(client))
    //				.retrieve()
    //				.bodyToMono(List.class)
    //				.block();
    //
    //		if (orgs != null && orgs.stream().anyMatch(org -> "spring-projects".equals(org.get("login")))) {
    //			return user;
    //		}
    //
    //		throw new OAuth2AuthenticationException(
    //				new OAuth2Error("invalid_token", "Not in Spring Team", "")
    //		);
    //	}
}