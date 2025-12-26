package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/fill-records")
@Tag(name = "Fill Level Records", description = "APIs for managing bin fill level records")
public class FillLevelRecordController {

    @PostMapping
    @Operation(summary = "Create fill record", description = "Create a new fill level record for a bin")
    public Map<String, String> createRecord() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Fill record created (stub)");
        return response;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fill record by ID", description = "Get a specific fill record")
    public Map<String, String> getRecordById(@PathVariable Long id) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Fill record " + id + " (stub)");
        return response;
    }
}