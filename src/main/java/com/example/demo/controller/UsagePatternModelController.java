package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/models")
@Tag(name = "Usage Pattern Models", description = "APIs for managing usage pattern models")
public class UsagePatternModelController {

    @PostMapping
    @Operation(summary = "Create usage model", description = "Create a new usage pattern model")
    public Map<String, String> createModel() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Usage model created (stub)");
        return response;
    }

    @GetMapping("/bin/{binId}")
    @Operation(summary = "Get model for bin", description = "Get latest usage model for a bin")
    public Map<String, String> getModelForBin(@PathVariable Long binId) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Model for bin " + binId + " (stub)");
        return response;
    }
}