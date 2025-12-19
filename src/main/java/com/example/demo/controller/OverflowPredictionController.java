package com.example.demo.controller;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/overflow-predictions")
public class OverflowPredictionController {

    private final OverflowPredictionService service;

    public OverflowPredictionController(OverflowPredictionService service) {
        this.service = service;
    }

    @PostMapping
    public OverflowPrediction create(@RequestBody OverflowPrediction prediction) {
        return service.create(prediction);
    }

    @GetMapping
    public List<OverflowPrediction> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public OverflowPrediction getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OverflowPrediction update(@PathVariable Long id, @RequestBody OverflowPrediction prediction) {
        return service.update(id, prediction);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
