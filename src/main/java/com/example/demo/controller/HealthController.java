package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Health Check", description = "API Health Check Endpoints")
public class HealthController {

    @GetMapping("/health")
    @Operation(summary = "Health check", description = "Returns API health status")
    public Map<String, Object> health() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "UP");
        response.put("service", "Bin Overflow Predictor API");
        response.put("timestamp", LocalDateTime.now().toString());
        response.put("version", "1.0.0");
        return response;
    }

    @GetMapping("/test")
    @Operation(summary = "Test endpoint", description = "Simple test endpoint")
    public Map<String, String> test() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "API is working!");
        response.put("status", "success");
        return response;
    }

    @GetMapping("/echo/{message}")
    @Operation(summary = "Echo endpoint", description = "Echoes back the message")
    public Map<String, String> echo(@PathVariable String message) {
        Map<String, String> response = new HashMap<>();
        response.put("echo", message);
        response.put("timestamp", LocalDateTime.now().toString());
        return response;
    }
}