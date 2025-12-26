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
    public Map<String, Object> register(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password) {
        
        try {
            // Register user using your existing service
            CustomUserDetailsService.DemoUser user = userDetailsService.registerUser(name, email, password);
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("message", "User registered successfully");
            response.put("userId", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            return response;
        } catch (RuntimeException e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", e.getMessage());
            return error;
        }
    }
    
    @PostMapping("/login")
    @Operation(summary = "Login user and get JWT token")
    public Map<String, Object> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        
        try {
            // Get user from your service
            CustomUserDetailsService.DemoUser user = userDetailsService.getByEmail(email);
            
            // In a real app, you would verify the password here
            // For demo, we'll trust the user exists
            
            // Create authentication
            UsernamePasswordAuthenticationToken auth = 
                new UsernamePasswordAuthenticationToken(email, password);
            
            // Generate JWT token
            String token = jwtTokenProvider.generateToken(auth, user.getId(), user.getRole(), user.getEmail());
            
            Map<String, Object> response = new HashMap<>();
            response.put("success", true);
            response.put("token", token);
            response.put("tokenType", "Bearer");
            response.put("userId", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("message", "Login successful");
            return response;
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("success", false);
            error.put("error", "Invalid credentials");
            return error;
        }
    }
    
    @GetMapping("/test-token")
    @Operation(summary = "Test token generation")
    public Map<String, String> testToken() {
        // For testing - generates a token with test user
        UsernamePasswordAuthenticationToken auth = 
            new UsernamePasswordAuthenticationToken("test@demo.com", "test123");
        
        String token = jwtTokenProvider.generateToken(auth, 999L, "USER", "test@demo.com");
        
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        response.put("message", "Test token generated");
        return response;
    }
}