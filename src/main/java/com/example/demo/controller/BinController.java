package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bins")
public class BinController {

    private final BinService binService;

    public BinController(BinService binService) {
        this.binService = binService;
    }

    @PostMapping
    public Bin save(@RequestBody Bin bin) {
        return binService.save(bin);
    }

    @GetMapping
    public List<Bin> findAll() {
        return binService.findAll();
    }

    @GetMapping("/{id}")
    public Bin findById(@PathVariable Long id) {
        return binService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        binService.delete(id);
    }
}
