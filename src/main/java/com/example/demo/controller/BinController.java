package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bins")
public class BinController {

    @Autowired
    private BinService binService;

    @PostMapping
    public ResponseEntity<Bin> createBin(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.createBin(bin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBin(@PathVariable Long id) {
        return ResponseEntity.ok(binService.getBinById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + id)));
    }

    @GetMapping
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin binDetails) {
        return ResponseEntity.ok(binService.updateBin(id, binDetails));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Bin> deactivateBin(@PathVariable Long id) {
        return ResponseEntity.ok(binService.deactivateBin(id));
    }
}
