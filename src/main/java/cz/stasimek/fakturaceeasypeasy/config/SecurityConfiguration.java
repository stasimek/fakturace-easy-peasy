package cz.stasimek.fakturaceeasypeasy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
public class SecurityConfiguration {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
				// URLs available without login.
				.authorizeRequests(
						a -> a.antMatchers("/", "/error", "/webjars/**").permitAll()
								.anyRequest().authenticated()
				)
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
				.oauth2Login();
		return http.build();
	}

}
