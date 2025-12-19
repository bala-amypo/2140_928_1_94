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
    public OverflowPrediction save(@RequestBody OverflowPrediction prediction) {
        return service.save(prediction);
    }

    @GetMapping
    public List<OverflowPrediction> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public OverflowPrediction findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
