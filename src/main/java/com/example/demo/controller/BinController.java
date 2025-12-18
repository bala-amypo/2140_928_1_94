package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
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
    public Bin create(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @GetMapping
    public List<Bin> getAll() {
        return binService.getAllBins();
    }

    @GetMapping("/{id}")
    public Bin getById(@PathVariable Long id) {
        return binService.getBinById(id);
    }

    @PutMapping("/{id}")
    public Bin update(@PathVariable Long id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        binService.deleteBin(id);
    }
}
