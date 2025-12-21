package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
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
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        UsagePatternModel saved = modelService.create(model);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable Long id,
                                                         @RequestBody UsagePatternModel model) {
        UsagePatternModel updated = modelService.update(id, model);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return new ResponseEntity<>(modelService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<UsagePatternModel>> getModelsByBin(@PathVariable Long binId) {
        return new ResponseEntity<>(modelService.getByBinId(binId), HttpStatus.OK);
    }
}