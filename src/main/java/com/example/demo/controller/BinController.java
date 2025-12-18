package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bins")
public class BinController {

    private final BinService service;

    public BinController(BinService service) {
        this.service = service;
    }

    @PostMapping
    public Bin create(@RequestBody Bin bin) {
        return service.create(bin);
    }

    @GetMapping
    public List<Bin> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Bin get(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public Bin update(@PathVariable Long id, @RequestBody Bin bin) {
        return service.update(id, bin);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
