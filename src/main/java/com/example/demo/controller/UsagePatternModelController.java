package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
@Tag(name = "Usage Pattern Models", description = "APIs for managing usage pattern models")
public class UsagePatternModelController {

    @Autowired
    private UsagePatternModelService usagePatternModelService;

    @PostMapping
    @Operation(summary = "Create usage model", description = "Create a new usage pattern model")
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        UsagePatternModel created = usagePatternModelService.createModel(model);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update usage model", description = "Update an existing usage pattern model")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable Long id, @RequestBody UsagePatternModel model) {
        UsagePatternModel updated = usagePatternModelService.updateModel(id, model);
        return ResponseEntity.ok(updated);
    }

    @GetMapping("/bin/{binId}")
    @Operation(summary = "Get model for bin", description = "Get latest usage model for a bin")
    public ResponseEntity<UsagePatternModel> getModelForBin(@PathVariable Long binId) {
        UsagePatternModel model = usagePatternModelService.getModelForBin(binId);
        return ResponseEntity.ok(model);
    }

    @GetMapping
    @Operation(summary = "Get all models", description = "List all usage pattern models")
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        List<UsagePatternModel> models = usagePatternModelService.getAllModels();
        return ResponseEntity.ok(models);
    }
}