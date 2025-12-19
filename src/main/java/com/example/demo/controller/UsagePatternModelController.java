package com.example.demo.controller;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/models")
public class UsagePatternModelController {

    private final UsagePatternModelService service;

    public UsagePatternModelController(UsagePatternModelService service) {
        this.service = service;
    }

    @PostMapping("/")
    public ResponseEntity<UsagePatternModel> createModel(@RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(service.createModel(model));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsagePatternModel> updateModel(@PathVariable long id,
                                                         @RequestBody UsagePatternModel model) {
        return ResponseEntity.ok(service.updateModel(id, model));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<UsagePatternModel> getModelForBin(@PathVariable long binId) {
        return ResponseEntity.ok(service.getModelForBin(binId));
    }

    @GetMapping("/")
    public ResponseEntity<List<UsagePatternModel>> getAllModels() {
        return ResponseEntity.ok(service.getAllModels());
    }
}
