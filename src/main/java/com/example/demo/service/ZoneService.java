package com.example.demo.service;

import com.example.demo.model.Zone;
import java.util.List;

public interface ZoneService {
    Zone createZone(Zone zone);
    Zone getZoneById(Long id);
    List<Zone> getAllZones();
    Zone updateZone(Long id, Zone zone);
    void deactivateZone(Long id);
}
