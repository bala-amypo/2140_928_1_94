package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    public ResponseEntity<Bin> create(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.create(bin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> get(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Bin>> getAll() {
        return ResponseEntity.ok(binService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> update(@PathVariable Long id, @RequestBody Bin bin) {
        return ResponseEntity.ok(binService.update(id, bin));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        binService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
