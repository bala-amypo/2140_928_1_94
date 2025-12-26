package com.example.demo.security;

import io.jsonwebtoken.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    private final String jwtSecret;
    
    public JwtTokenProvider() {
        this.jwtSecret = "VerySecretKeyForJwtDemo1234567890";
    }
    
    public JwtTokenProvider(String secret) {
        this.jwtSecret = secret;
    }

    public String generateToken(UsernamePasswordAuthenticationToken authentication, 
                               Long userId, String role, String email) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + 3600000); // 1 hour
        
        return Jwts.builder()
            .setSubject(email)
            .claim("userId", userId)
            .claim("role", role)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }
}