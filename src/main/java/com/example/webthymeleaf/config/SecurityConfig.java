package com.example.webthymeleaf.config;

import com.example.webthymeleaf.security.CustomUserDetailsService;
import com.example.webthymeleaf.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

                // Rutas públicas — registro y login
                .requestMatchers("/auth/**").permitAll()

                // Rutas de usuario autenticado
                .requestMatchers("/usuarios/perfil/**").authenticated()
                .requestMatchers("/usuarios/bolitos/**").authenticated()
                .requestMatchers("/usuarios/historial/**").authenticated()

                // Reservas — cualquier usuario autenticado
                .requestMatchers("/reservas/**").authenticated()

                // Pistas y franjas — lectura pública, escritura solo admin
                .requestMatchers("GET", "/pistas/**").permitAll()
                .requestMatchers("GET", "/franjas/**").permitAll()
                .requestMatchers("POST", "/pistas/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/pistas/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/pistas/**").hasRole("ADMIN")
                .requestMatchers("POST", "/franjas/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/franjas/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/franjas/**").hasRole("ADMIN")

                // Valoraciones — cualquier usuario autenticado
                .requestMatchers("/valoraciones/**").authenticated()

                // Promociones — lectura autenticado, escritura solo admin
                .requestMatchers("GET", "/promociones/**").authenticated()
                .requestMatchers("POST", "/promociones/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/promociones/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/promociones/**").hasRole("ADMIN")

                // Recompensas — lectura autenticado, escritura solo admin
                .requestMatchers("GET", "/recompensas/**").authenticated()
                .requestMatchers("POST", "/recompensas/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/recompensas/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/recompensas/**").hasRole("ADMIN")

                // Eventos — lectura pública, escritura solo admin
                .requestMatchers("GET", "/eventos/**").permitAll()
                .requestMatchers("POST", "/eventos/**").hasRole("ADMIN")
                .requestMatchers("PUT", "/eventos/**").hasRole("ADMIN")
                .requestMatchers("DELETE", "/eventos/**").hasRole("ADMIN")

                // Participacion en eventos — usuario autenticado
                .requestMatchers("/eventos/participar/**").authenticated()

                // Notificaciones — solo el propio usuario
                .requestMatchers("/notificaciones/**").authenticated()

                // Panel de administración completo
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