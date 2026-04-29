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
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                .requestMatchers("/auth/**").permitAll()

                .requestMatchers("/usuarios/perfil/**").authenticated()
                .requestMatchers("/usuarios/bolitos/**").authenticated()
                .requestMatchers("/usuarios/historial/**").authenticated()
                
                .requestMatchers("/reservas", "/reservas/**").authenticated()

                .requestMatchers(HttpMethod.GET, "/pistas/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/franjas/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/pistas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/pistas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/pistas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/franjas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/franjas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/franjas/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/promociones/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/promociones/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/promociones/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/promociones/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/recompensas/**").authenticated()
                .requestMatchers(HttpMethod.POST, "/recompensas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/recompensas/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/recompensas/**").hasRole("ADMIN")

                .requestMatchers(HttpMethod.GET, "/eventos/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/eventos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/eventos/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/eventos/**").hasRole("ADMIN")

                .requestMatchers("/eventos/participar/**").authenticated()
                .requestMatchers("/notificaciones/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")

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