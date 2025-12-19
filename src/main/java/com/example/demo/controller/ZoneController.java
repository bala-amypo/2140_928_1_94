package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService service;

    public ZoneController(ZoneService service) {
        this.service = service;
    }

    @PostMapping
    public Zone create(@RequestBody Zone zone) {
        return service.create(zone);
    }

    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id, @RequestBody Zone zone) {
        return service.update(id, zone);
    }

    @PutMapping("/{id}/deactivate")
    public Zone deactivate(@PathVariable Long id) {
        return service.deactivate(id);
    }

    @GetMapping("/{id}")
    public Zone getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Zone> getAll() {
        return service.getAll();
    }
}
