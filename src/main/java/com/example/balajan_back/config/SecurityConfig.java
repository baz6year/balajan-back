package com.example.balajan_back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults()) // если у тебя есть CORS-конфиг — он подцепится
                .authorizeHttpRequests(auth -> auth
                        // 👉 пока ВСЕ api-ручки открыты, включая админские
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().permitAll()
                )
                // отключаем стандартную форму логина и basic-auth,
                // чтобы Spring не редиректил на /login
                .formLogin(form -> form.disable())
                .httpBasic(basic -> basic.disable());

        return http.build();
    }

}
