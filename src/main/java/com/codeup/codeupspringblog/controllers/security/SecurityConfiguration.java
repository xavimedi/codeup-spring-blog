package com.codeup.codeupspringblog.controllers.security;

import com.codeup.codeupspringblog.services.AdUserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

	private AdUserDetailsLoader adUsersLoader;

	public SecurityConfiguration(AdUserDetailsLoader adUsersLoader) {
		this.adUsersLoader = adUsersLoader;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests
						/* Pages that require authentication
						 * only authenticated users can create and edit ads */
						.requestMatchers("/posts/create", "/posts/*/edit", "/ads/create", "/ads/*/edit").authenticated()
						/* Pages that do not require authentication
						 * anyone can visit the home page, register, login, and view ads */
						.requestMatchers("/", "/posts", "/posts/*", "/ads", "/ads/*", "/sign-up", "/login").permitAll()
						// allow loading of static resources
						.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
				)
				/* Login configuration */
				.formLogin((login) -> login.loginPage("/login").defaultSuccessUrl("/ads"))
				/* Logout configuration */
				.logout((logout) -> logout.logoutSuccessUrl("/login"))
				.httpBasic(withDefaults());
		return http.build();
	}

}
