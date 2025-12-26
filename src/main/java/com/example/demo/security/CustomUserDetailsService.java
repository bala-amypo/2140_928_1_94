package com.example.demo.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    
    private final Map<String, DemoUser> users = new HashMap<>();
    
    public CustomUserDetailsService() {
        // Default admin user
        users.put("admin@city.com", 
            new DemoUser(1L, "Admin User", "admin@city.com", "admin123", "ADMIN"));
    }
    
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        DemoUser demoUser = users.get(email);
        if (demoUser == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        
        return User.withUsername(demoUser.getEmail())
            .password(demoUser.getPassword())
            .roles(demoUser.getRole())
            .build();
    }
    
    public DemoUser getByEmail(String email) {
        return users.get(email);
    }
    
    public DemoUser registerUser(String name, String email, String password) {
        if (users.containsKey(email)) {
            throw new RuntimeException("User with email " + email + " already exists");
        }
        
        DemoUser user = new DemoUser((long) (users.size() + 1), name, email, password, "USER");
        users.put(email, user);
        return user;
    }
    
    public static class DemoUser {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String role;
        
        // Constructor, getters, and setters
        public DemoUser(Long id, String name, String email, String password, String role) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
            this.role = role;
        }
        
        // Getters and setters
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }
}