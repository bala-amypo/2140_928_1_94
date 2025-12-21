package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/overflow-predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OverflowPrediction> create(@RequestBody OverflowPrediction prediction) {
        return ResponseEntity.ok(service.create(prediction));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OverflowPrediction> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<OverflowPrediction>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    // New endpoints to match the question requirements

    @PostMapping("/generate/{binId}")
    public ResponseEntity<OverflowPrediction> generate(@PathVariable Long binId) {
        return ResponseEntity.ok(service.generatePrediction(binId));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<OverflowPrediction>> getByBin(@PathVariable Long binId) {
        return ResponseEntity.ok(service.getByBin(binId));
    }

    @GetMapping("/zone/{zoneId}/latest")
    public ResponseEntity<OverflowPrediction> getLatestForZone(@PathVariable Long zoneId) {
        return ResponseEntity.ok(service.getLatestForZone(zoneId));
    }
}
