package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping
    public UsagePatternModel create(@RequestBody UsagePatternModel model) {
        return service.create(model);
    }

    @PutMapping("/{id}")
    public UsagePatternModel update(@PathVariable Long id, @RequestBody UsagePatternModel model) {
        return service.update(id, model);
    }

    @GetMapping("/bin/{binId}")
    public UsagePatternModel getByBin(@PathVariable Long binId) {
        return service.getByBin(binId);
    }

    @GetMapping
    public List<UsagePatternModel> getAll() {
        return service.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
