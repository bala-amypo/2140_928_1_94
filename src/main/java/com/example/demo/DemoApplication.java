package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}

com/example/demo
├── config
│   ├── SwaggerConfig.java
│   └── SecurityConfig.java   ← NEW
├── controller
│   ├── AuthController.java   ← NEW
│   ├── UserController.java
│   ├── BinController.java
│   └── ...
├── security
│   ├── JwtTokenProvider.java ← NEW
│   └── CustomUserDetailsService.java ← NEW
├── model
├── repository
├── service
└── DemoApplication.java
