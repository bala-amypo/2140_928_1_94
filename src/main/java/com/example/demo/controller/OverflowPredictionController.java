package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping("/generate/{binId}")
    public ResponseEntity<OverflowPrediction> generate(@PathVariable Long binId) {
        return ResponseEntity.ok(service.generatePrediction(binId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OverflowPrediction> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<OverflowPrediction>> byBin(@PathVariable Long binId) {
        return ResponseEntity.ok(service.getByBin(binId));
    }

    @GetMapping("/zone/{zoneId}/latest")
    public ResponseEntity<List<OverflowPrediction>> latest(@PathVariable Long zoneId) {
        return ResponseEntity.ok(service.getLatestForZone(zoneId));
    }
}
