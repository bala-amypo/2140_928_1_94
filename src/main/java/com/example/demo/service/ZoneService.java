package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;

public interface ZoneService {

    Zone createZone(Zone zone);

    Zone updateZone(long id, Zone zone);

    Zone getZoneById(long id);

    List<Zone> getAllZones();

    void deactivateZone(long id);
}
