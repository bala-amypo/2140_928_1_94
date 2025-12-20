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
    public ResponseEntity<Bin> createBin(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.createBin(bin));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBin(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getBinById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        Bin updated = binService.updateBin(id, bin);
        return ResponseEntity.ok(updated);
    }
    
    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        return ResponseEntity.ok().build();
    }
}