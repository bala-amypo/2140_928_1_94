package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usage-patterns")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public UsagePatternModel create(@RequestBody UsagePatternModel model) {
        return service.create(model);
    }

    @GetMapping
    public List<UsagePatternModel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public UsagePatternModel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public UsagePatternModel update(@PathVariable Long id, @RequestBody UsagePatternModel model) {
        return service.update(id, model);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
