package com.example.demo.controller;

import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "User authentication endpoints")
public class AuthController {
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    
    @Autowired
    private CustomUserDetailsService userDetailsService;
    
    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public Map<String, String> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        
        try {
            // This uses the service you already have in tests
            CustomUserDetailsService.DemoUser user = userDetailsService.registerUser(name, email, password);
            
            Map<String, String> response = new HashMap<>();
            response.put("message", "User registered successfully");
            response.put("userId", user.getId().toString());
            response.put("email", user.getEmail());
            return response;
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return error;
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user and get JWT token")
    public Map<String, String> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        try {
            // Get user from your service
            CustomUserDetailsService.DemoUser user = userDetailsService.getByEmail(email);
            
            // Create authentication (simplified - real app would verify password)
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(email, password);
            
            // Generate token
            String token = jwtTokenProvider.generateToken(auth, user.getId(), user.getRole(), user.getEmail());
            
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            response.put("userId", user.getId().toString());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("message", "Login successful");
            return response;
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Invalid credentials");
            return error;
        }
    }
}