package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService modelService;

    public UsagePatternModelController(UsagePatternModelService modelService) {
        this.modelService = modelService;
    }

    @PostMapping
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        UsagePatternModel createdModel = modelService.createModel(model);
        return ResponseEntity.status(201).body(createdModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable Long id,
                                                         @RequestBody UsagePatternModel model) {
        UsagePatternModel updatedModel = modelService.updateModel(id, model);
        return ResponseEntity.ok(updatedModel);
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getModelByBin(@PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getModelForBin(binId));
    }
}