package com.example.balajan_back.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final SessionAuthFilter sessionAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()) // берёт настройки из CorsConfig
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/api/auth/login",
                                "/api/news/**",
                                "/api/contests/**"
                        ).permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN") // 👈 тут
                        .anyRequest().permitAll()
                )
                // наш фильтр, который читает USER_ID из сессии
                .addFilterBefore(sessionAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // отключаем формы Spring Security и basic-логин (мы логинимся через свой контроллер)
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
