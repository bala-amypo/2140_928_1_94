package com.example.demo.controller;

import com.example.demo.entity.Bin;
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
        return binService.create(bin);
    }

    @GetMapping
    public List<Bin> getAll() {
        return binService.getAll();
    }

    @GetMapping("/{id}")
    public Bin getById(@PathVariable Long id) {
        return binService.getById(id);
    }

    @PutMapping("/{id}")
    public Bin update(@PathVariable Long id, @RequestBody Bin bin) {
        return binService.update(id, bin);
    }

    @PutMapping("/{id}/deactivate")
    public void deactivate(@PathVariable Long id) {
        binService.deactivate(id);
    }
}
    