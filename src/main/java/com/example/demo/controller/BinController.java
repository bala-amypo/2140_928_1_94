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

    @PostMapping("/")
    public Bin createBin(@RequestBody Bin bin) {
        return binService.createBin(bin);
    }

    @PutMapping("/{id}")
    public Bin updateBin(@PathVariable long id, @RequestBody Bin bin) {
        return binService.updateBin(id, bin);
    }

    @GetMapping("/{id}")
    public Bin getBinById(@PathVariable long id) {
        return binService.getBinById(id);
    }

    @GetMapping("/")
    public List<Bin> getAllBins() {
        return binService.getAllBins();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateBin(@PathVariable long id) {
        binService.deactivateBin(id);
    }
}
