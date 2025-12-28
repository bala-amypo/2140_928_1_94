package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
@Tag(name = "Overflow Predictions", description = "APIs for generating and retrieving overflow predictions")
public class OverflowPredictionController {

    @Autowired
    private OverflowPredictionService overflowPredictionService;

    @PostMapping("/generate/{binId}")
    @Operation(summary = "Generate prediction", description = "Generate overflow prediction for a bin")
    public ResponseEntity<OverflowPrediction> generatePrediction(@PathVariable Long binId) {
        OverflowPrediction prediction = overflowPredictionService.generatePrediction(binId);
        return new ResponseEntity<>(prediction, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get prediction by ID", description = "Get a specific overflow prediction")
    public ResponseEntity<OverflowPrediction> getPredictionById(@PathVariable Long id) {
        OverflowPrediction prediction = overflowPredictionService.getPredictionById(id);
        return ResponseEntity.ok(prediction);
    }

    @GetMapping("/bin/{binId}")
    @Operation(summary = "Get predictions for bin", description = "Get all predictions for a specific bin")
    public ResponseEntity<List<OverflowPrediction>> getPredictionsForBin(@PathVariable Long binId) {
        List<OverflowPrediction> predictions = overflowPredictionService.getPredictionsForBin(binId);
        return ResponseEntity.ok(predictions);
    }

    @GetMapping("/zone/{zoneId}/latest")
    @Operation(summary = "Get latest zone predictions", description = "Get latest predictions for a zone")
    public ResponseEntity<List<OverflowPrediction>> getLatestPredictionsForZone(@PathVariable Long zoneId) {
        List<OverflowPrediction> predictions = overflowPredictionService.getLatestPredictionsForZone(zoneId);
        return ResponseEntity.ok(predictions);
    }
}