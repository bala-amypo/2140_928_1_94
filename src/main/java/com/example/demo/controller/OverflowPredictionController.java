package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overflow-predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService overflowPredictionService;

    public OverflowPredictionController(OverflowPredictionService overflowPredictionService) {
        this.overflowPredictionService = overflowPredictionService;
    }

    @PostMapping
    public OverflowPrediction create(@RequestBody OverflowPrediction prediction) {
        return overflowPredictionService.create(prediction);
    }

    @GetMapping
    public List<OverflowPrediction> getAll() {
        return overflowPredictionService.getAll();
    }

    @GetMapping("/{id}")
    public OverflowPrediction getById(@PathVariable Long id) {
        return overflowPredictionService.getById(id);
    }

    @PutMapping("/{id}")
    public OverflowPrediction update(
            @PathVariable Long id,
            @RequestBody OverflowPrediction prediction
    ) {
        return overflowPredictionService.update(id, prediction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        overflowPredictionService.delete(id);
    }
}
