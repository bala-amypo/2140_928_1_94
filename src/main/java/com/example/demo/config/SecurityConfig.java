package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disable CSRF for API testing
            .authorizeRequests()
            .antMatchers(
                "/", 
                "/swagger-ui/**", 
                "/v3/api-docs/**", 
                "/api/**",
                "/actuator/**"
            ).permitAll()  // Allow public access to ALL API endpoints
            .anyRequest().authenticated()  // This line won't matter since we permit all above
            .and()
            .httpBasic().disable();  // Disable HTTP Basic auth
        
        return http.build();
    }
}