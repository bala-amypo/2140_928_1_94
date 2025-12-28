package com.example.demo.controller;

import com.example.demo.model.Zone;
import com.example.demo.service.ZoneService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/zones")
@Tag(name = "Zone Management", description = "APIs for managing zones")
public class ZoneController {

    @Autowired
    private ZoneService zoneService;

    @PostMapping
    @Operation(summary = "Create zone", description = "Create a new zone")
    public ResponseEntity<Zone> createZone(@RequestBody Zone zone) {
        Zone created = zoneService.createZone(zone);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get zone by ID", description = "Get a specific zone")
    public ResponseEntity<Zone> getZoneById(@PathVariable Long id) {
        Zone zone = zoneService.getZoneById(id);
        return ResponseEntity.ok(zone);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update zone", description = "Update an existing zone")
    public ResponseEntity<Zone> updateZone(@PathVariable Long id, @RequestBody Zone zone) {
        Zone updated = zoneService.updateZone(id, zone);
        return ResponseEntity.ok(updated);
    }

    @GetMapping
    @Operation(summary = "Get all zones", description = "List all zones")
    public ResponseEntity<List<Zone>> getAllZones() {
        List<Zone> zones = zoneService.getAllZones();
        return ResponseEntity.ok(zones);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate zone", description = "Deactivates a zone")
    public ResponseEntity<Map<String, String>> deactivateZone(@PathVariable Long id) {
        zoneService.deactivateZone(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Zone deactivated successfully");
        response.put("zoneId", id.toString());
        return ResponseEntity.ok(response);
    }
}