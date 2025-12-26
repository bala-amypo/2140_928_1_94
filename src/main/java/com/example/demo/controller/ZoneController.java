package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/zones")
@Tag(name = "Zone Management", description = "APIs for managing zones")
public class ZoneController {

    @PostMapping
    @Operation(summary = "Create zone", description = "Create a new zone")
    public Map<String, String> createZone() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Zone created (stub)");
        return response;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get zone by ID", description = "Get a specific zone")
    public Map<String, String> getZoneById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Zone " + id + " (stub)");
        return response;
    }

    @GetMapping
    @Operation(summary = "Get all zones", description = "List all zones")
    public Map<String, String> getAllZones() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "All zones (stub)");
        return response;
    }
}