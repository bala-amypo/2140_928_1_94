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

    @PostMapping("/")
    public ResponseEntity<Bin> createBin(@RequestBody Bin bin) {
        return ResponseEntity.ok(binService.createBin(bin));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bin> updateBin(@PathVariable long id, @RequestBody Bin bin) {
        return ResponseEntity.ok(binService.updateBin(id, bin));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bin> getBin(@PathVariable long id) {
        return ResponseEntity.ok(binService.getBinById(id));
    }

    @GetMapping("/")
    public ResponseEntity<List<Bin>> getAllBins() {
        return ResponseEntity.ok(binService.getAllBins());
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateBin(@PathVariable long id) {
        binService.deactivateBin(id);
        return ResponseEntity.ok().build();
    }
}
