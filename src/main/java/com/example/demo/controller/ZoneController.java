package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Zone> create(@Valid @RequestBody Zone zone) {
        return ResponseEntity.ok(zoneService.create(zone));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Zone> get(@PathVariable Long id) {
        return ResponseEntity.ok(zoneService.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<Zone>> getAll() {
        return ResponseEntity.ok(zoneService.getAll());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Zone> update(
            @PathVariable Long id,
            @Valid @RequestBody Zone zone) {

        return ResponseEntity.ok(zoneService.update(id, zone));
    }

    @PutMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(@PathVariable Long id) {
        zoneService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
