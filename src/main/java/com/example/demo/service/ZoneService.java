package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;
import java.util.Optional;

public interface ZoneService {
    Zone createZone(Zone zone);
    Optional<Zone> getZoneById(Long id);
    List<Zone> getAllZones();
    Zone updateZone(Long id, Zone zone);
    Zone deactivateZone(Long id);
}
