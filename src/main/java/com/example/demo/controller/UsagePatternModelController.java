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
        return ResponseEntity.ok(modelService.createModel(model));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable Long id, @RequestBody UsagePatternModel model) {
        UsagePatternModel updated = modelService.updateModel(id, model);
        return ResponseEntity.ok(updated);
    }
    
    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getModelForBin(@PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getModelForBin(binId));
    }
    
    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }
}