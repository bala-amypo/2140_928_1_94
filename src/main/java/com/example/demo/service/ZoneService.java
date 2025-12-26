package com.example.demo.service;

import com.example.demo.model.Zone;

public interface ZoneService {
    Zone createZone(Zone zone);
    Zone getZoneById(Long id);
    Zone updateZone(Long id, Zone zone);
    void deactivateZone(Long id);
}