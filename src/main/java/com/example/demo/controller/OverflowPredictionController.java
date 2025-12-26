package com.example.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/predictions")
@Tag(name = "Overflow Predictions", description = "APIs for generating and retrieving overflow predictions")
public class OverflowPredictionController {

    @PostMapping("/generate/{binId}")
    @Operation(summary = "Generate prediction", description = "Generate overflow prediction for a bin")
    public Map<String, String> generatePrediction(@PathVariable Long binId) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Prediction generated for bin " + binId + " (stub)");
        return response;
    }

    @GetMapping("/zone/{zoneId}/latest")
    @Operation(summary = "Get latest zone predictions", description = "Get latest predictions for a zone")
    public Map<String, String> getLatestPredictionsForZone(@PathVariable Long zoneId) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Latest predictions for zone " + zoneId + " (stub)");
        return response;
    }
}