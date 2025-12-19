package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
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
    public OverflowPrediction generatePrediction(@PathVariable long binId) {
        return service.generatePrediction(binId);
    }

    @GetMapping("/{id}")
    public OverflowPrediction getPrediction(@PathVariable long id) {
        return service.getPredictionById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<OverflowPrediction> getPredictionsForBin(@PathVariable long binId) {
        return service.getPredictionsForBin(binId);
    }

    @GetMapping("/zone/{zoneId}/latest")
    public List<OverflowPrediction> getLatestPredictionsForZone(@PathVariable long zoneId) {
        return service.getLatestPredictionsForZone(zoneId);
    }
}
