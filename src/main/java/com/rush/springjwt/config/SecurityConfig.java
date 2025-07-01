package com.rush.springjwt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())   // disable CSRF for APIs
                .authorizeHttpRequests(auth-> auth.requestMatchers("/", "/auth/**", "/health").permitAll()    // allow register/login
                        .anyRequest().authenticated())                                            // protect other routes
                .httpBasic(Customizer.withDefaults());                                            // temporarily allow basic auth for now

        return http.build();
    }

}
