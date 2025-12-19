package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;

    public ZoneServiceImpl(ZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zone createZone(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public Zone updateZone(long id, Zone zone) {
        Zone existing = getZoneById(id);
        existing.setZoneName(zone.getZoneName());
        return repository.save(existing);
    }

    @Override
    public Zone getZoneById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Zone not found"));
    }

    @Override
    public List<Zone> getAllZones() {
        return repository.findAll();
    }

    @Override
    public void deactivateZone(long id) {
        Zone z = getZoneById(id);
        z.setActive(false);
        repository.save(z);
    }
}
