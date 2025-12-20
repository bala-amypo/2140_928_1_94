package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    @Autowired
    private UsagePatternModelService modelService;

    @PostMapping
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(modelService.createModel(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable Long id,
                                                         @RequestBody UsagePatternModel modelDetails) {
        return ResponseEntity.ok(modelService.updateModel(id, modelDetails));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getModelForBin(@PathVariable Long binId) {
        return ResponseEntity.ok(modelService.getModelForBin(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for bin " + binId)));
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(modelService.getAllModels());
    }
}
