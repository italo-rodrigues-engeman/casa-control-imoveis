package com.indux.realestate.modules.realestate.infra.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .cors(cors -> {})
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users", "/users/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/login", "/auth/login/**").permitAll()

                        .requestMatchers(HttpMethod.GET, "/properties/**").hasAnyRole("ADMIN", "BROKER", "CLIENT")
                        .requestMatchers(HttpMethod.POST, "/properties").hasAnyRole("ADMIN", "BROKER")
                        .requestMatchers(HttpMethod.PUT, "/properties/**").hasAnyRole("ADMIN", "BROKER")
                        .requestMatchers(HttpMethod.PATCH, "/properties/**").hasAnyRole("ADMIN", "BROKER")

                        .requestMatchers("/favorites/**").hasRole("CLIENT")

                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}