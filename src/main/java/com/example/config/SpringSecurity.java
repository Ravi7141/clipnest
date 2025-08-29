package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurity {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF (for Postman testing)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**").permitAll() // allow register/login/logout
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults()) // you can keep this ON or disable
                .formLogin(Customizer.withDefaults()); // you can keep this ON or disable

        return http.build();
    }
}
