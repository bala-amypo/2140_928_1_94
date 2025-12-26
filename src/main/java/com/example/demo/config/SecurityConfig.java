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
            // Disable CSRF for API testing
            .csrf().disable()
            
            // Configure authorization rules
            .authorizeRequests()
                // Allow public access to Swagger, OpenAPI, and authentication endpoints
                .antMatchers(
                    "/",
                    "/index.html",
                    "/swagger-ui/**",
                    "/swagger-ui.html",
                    "/v3/api-docs/**",
                    "/swagger-resources/**",
                    "/webjars/**",
                    "/auth/**",           // Authentication endpoints
                    "/api/health",        // Health check
                    "/api/test",          // Test endpoint
                    "/api/echo/**"        // Echo endpoint
                ).permitAll()
                
                // ALL other API endpoints require authentication
                .antMatchers("/api/**").authenticated()
                
                // Any other request needs authentication
                .anyRequest().authenticated()
            .and()
            
            // Disable form login (we're using JWT)
            .formLogin().disable()
            
            // Disable HTTP Basic auth
            .httpBasic().disable()
            
            // Disable logout (we'll handle it in our API)
            .logout().disable();
        
        return http.build();
    }
}