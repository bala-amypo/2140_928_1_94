package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/zones")
public class ZoneController {

    private final ZoneService zoneService;

    public ZoneController(ZoneService zoneService) {
        this.zoneService = zoneService;
    }

    @PostMapping
    public Zone save(@RequestBody Zone zone) {
        return zoneService.save(zone);
    }

    @GetMapping
    public List<Zone> findAll() {
        return zoneService.findAll();
    }

    @GetMapping("/{id}")
    public Zone findById(@PathVariable Long id) {
        return zoneService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        zoneService.delete(id);
    }
}
