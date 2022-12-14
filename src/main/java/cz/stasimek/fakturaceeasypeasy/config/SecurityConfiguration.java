package cz.stasimek.fakturaceeasypeasy.config;

import cz.stasimek.fakturaceeasypeasy.config.user.AppOAuth2User;
import cz.stasimek.fakturaceeasypeasy.config.user.AppOidcUser;
import cz.stasimek.fakturaceeasypeasy.entity.User;
import cz.stasimek.fakturaceeasypeasy.enumeration.Provider;
import cz.stasimek.fakturaceeasypeasy.exception.ApplicationException;
import cz.stasimek.fakturaceeasypeasy.service.UserService;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.context.SecurityContextHolderFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class SecurityConfiguration {

	@Autowired
	private UserService userService;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		return http
				// URLs available without login.
				.authorizeRequests(
						a -> a.antMatchers("/", "/api/login/error", "/static/**", "/api/**", "/**").permitAll()
								.anyRequest().authenticated()
				)
				// On AJAX request 401 instead of the default redirecting to a login page.
				.exceptionHandling(
						e -> e.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
				)
				// Spring Security has built in support for a /logout endpoint
				// which clear the session and invalidate the cookie.
				.logout(
						l -> l.logoutSuccessUrl("/").permitAll()
				)
				// Create cookie XSRF-TOKEN.
				.csrf(
						c -> c.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
				)
				// On login save "Referer" HTTP header to session as "login.redirectUri".
				.addFilterBefore(
						(request, response, chain) -> {
							HttpServletRequest r = ((HttpServletRequest) request);
							String uri = r.getRequestURI();
							if (uri != null && uri.startsWith("/oauth2/authorization/")) {
								r.getSession().setAttribute("login.redirectUri", r.getHeader("Referer"));
							}
							chain.doFilter(request, response);
						},
						SecurityContextHolderFilter.class
				)
				.oauth2Login(
						o -> {
							// On failure set session "login.errorMessage", than redirect to login page.
							o.failureHandler(
									(request, response, exception) -> {
										request.getSession().setAttribute("login.errorMessage", exception.getMessage());
										AuthenticationFailureHandler handler = new SimpleUrlAuthenticationFailureHandler("/");
										handler.onAuthenticationFailure(request, response, exception);
									}
							);
							// On success redirect user to original URL stored in session "login.redirectUri".
							o.successHandler(
									(request, response, authentication) -> {
										String redirectUri = (String) request.getSession().getAttribute("login.redirectUri");
										request.getSession().removeAttribute("login.redirectUri");
										SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler(redirectUri);
										handler.onAuthenticationSuccess(request, response, authentication);
									}
							);
						}
				)
				.build();
	}

	@Bean
	public WebClient rest(
			ClientRegistrationRepository clients, OAuth2AuthorizedClientRepository authz
	) {
		return WebClient
				.builder()
				.filter(new ServletOAuth2AuthorizedClientExchangeFilterFunction(clients, authz))
				.build();
	}

	/**
	 * This is triggered after Github "social" user logged in.
	 */
	@Bean
	public OAuth2UserService<OAuth2UserRequest, OAuth2User> oauth2UserService(WebClient rest) {
		DefaultOAuth2UserService delegate = new DefaultOAuth2UserService();
		return request -> {
			return handleUserLogin(request, delegate, rest);
		};
	}

	/**
	 * This is triggered after Google "social" user logged in.
	 */
	@Bean
	public OAuth2UserService<OidcUserRequest, OidcUser> oidcUserService(WebClient rest) {
		final OidcUserService delegate = new OidcUserService();
		return request -> {
			OAuth2User user = handleUserLogin(request, delegate, rest);
			if (user instanceof OidcUser) {
				return (OidcUser) user;
			}
			throw new ApplicationException(
					String.format(
							"Instance of OidcUser expected, get %s.",
							user != null ? user.getClass().getSimpleName() : "null"
					)
			);
		};
	}

	private OAuth2User handleUserLogin(
			OAuth2UserRequest request, OAuth2UserService delegate, WebClient rest
	) {
		OAuth2User user = delegate.loadUser(request);
		Provider provider = Provider.valueOfCaseInsensitive(
				request.getClientRegistration().getRegistrationId()
		);
		// Check that user is member of Github's Spring Team
		//if (provider == Provider.GITHUB) {
		//	return checkUserIsMemberOfSpringTeam(request, user, rest);
		//}
		User appUser = userService.createUserIfNotExist(provider, user);
		if (user instanceof OidcUser) {
			return new AppOidcUser((OidcUser) user, appUser);
		} else {
			return new AppOAuth2User(user, appUser);
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
