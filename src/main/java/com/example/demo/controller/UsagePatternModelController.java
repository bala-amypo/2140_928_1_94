package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage-pattern-models")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public UsagePatternModel save(@RequestBody UsagePatternModel model) {
        return service.save(model);
    }

    @GetMapping
    public List<UsagePatternModel> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public UsagePatternModel findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
