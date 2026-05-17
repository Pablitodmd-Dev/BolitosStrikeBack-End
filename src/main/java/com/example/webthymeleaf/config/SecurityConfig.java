package com.example.webthymeleaf.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.webthymeleaf.security.CustomUserDetailsService;
import com.example.webthymeleaf.security.JwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private JwtAuthenticationFilter jwtAuthenticationFilter;

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
			.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.authorizeHttpRequests(auth -> auth
					.requestMatchers("/auth/**").permitAll()

					.requestMatchers(HttpMethod.POST, "/usuarios/worker").hasRole("ADMIN")
					.requestMatchers(HttpMethod.GET, "/usuarios/**").authenticated()
					.requestMatchers(HttpMethod.PUT, "/usuarios/**").authenticated()
					.requestMatchers(HttpMethod.DELETE, "/usuarios/**").hasRole("ADMIN")

					.requestMatchers("/canjes", "/canjes/**").authenticated()
					.requestMatchers("/reservas", "/reservas/**").hasAnyRole("ADMIN", "WORKER", "USER")
					.requestMatchers("/valoraciones", "/valoraciones/**").authenticated()
					.requestMatchers(HttpMethod.DELETE, "/valoraciones/**").hasRole("ADMIN")

					.requestMatchers(HttpMethod.GET, "/pistas/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/pistas/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/pistas/**").hasAnyRole("ADMIN", "WORKER")
					.requestMatchers(HttpMethod.DELETE, "/pistas/**").hasRole("ADMIN")

					.requestMatchers(HttpMethod.GET, "/franjas", "/franjas/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/franjas", "/franjas/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/franjas", "/franjas/**").hasAnyRole("ADMIN", "WORKER")
					.requestMatchers(HttpMethod.DELETE, "/franjas", "/franjas/**").hasRole("ADMIN")

					.requestMatchers(HttpMethod.GET, "/recompensas/**").authenticated()
					.requestMatchers(HttpMethod.POST, "/recompensas/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/recompensas/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/recompensas/**").hasRole("ADMIN")

					.requestMatchers(HttpMethod.GET, "/eventos/**").permitAll()
					.requestMatchers(HttpMethod.POST, "/eventos/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.PUT, "/eventos/**").hasRole("ADMIN")
					.requestMatchers(HttpMethod.DELETE, "/eventos/**").hasRole("ADMIN")

					.requestMatchers("/participaciones", "/participaciones/**").authenticated()
					.requestMatchers("/notificaciones/**").authenticated()

					.anyRequest().authenticated()
					)
				.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}