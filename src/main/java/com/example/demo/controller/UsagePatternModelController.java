package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<UsagePatternModel> createModel(
            @Valid @RequestBody UsagePatternModel model) {
        UsagePatternModel savedModel = modelService.create(model);
        return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(
            @PathVariable Long id,
            @Valid @RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(modelService.update(id, model));
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(modelService.getAll());
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<UsagePatternModel>> getModelsByBin(
            @PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getByBinId(binId));
    }
}
