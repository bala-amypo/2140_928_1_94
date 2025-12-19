package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public Zone create(@RequestBody Zone zone) {
        return zoneService.create(zone);
    }

    @GetMapping
    public List<Zone> getAll() {
        return zoneService.getAll();
    }

    @GetMapping("/{id}")
    public Zone getById(@PathVariable Long id) {
        return zoneService.getById(id);
    }

    @PutMapping("/{id}")
    public Zone update(@PathVariable Long id, @RequestBody Zone zone) {
        return zoneService.update(id, zone);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        zoneService.delete(id);
    }
}
