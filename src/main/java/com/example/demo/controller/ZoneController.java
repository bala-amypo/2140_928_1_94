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

    @PostMapping("/")
    public Zone createZone(@RequestBody Zone zone) {
        return service.createZone(zone);
    }

    @PutMapping("/{id}")
    public Zone updateZone(@PathVariable long id, @RequestBody Zone zone) {
        return service.updateZone(id, zone);
    }

    @GetMapping("/{id}")
    public Zone getZoneById(@PathVariable long id) {
        return service.getZoneById(id);
    }

    @GetMapping("/")
    public List<Zone> getAllZones() {
        return service.getAllZones();
    }

    @PutMapping("/{id}/deactivate")
    public void deactivateZone(@PathVariable long id) {
        service.deactivateZone(id);
    }
}
