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

    public Zone createZone(Zone zone) {
        return repository.save(zone);
    }

    public List<Zone> getAllZones() {
        return repository.findAll();
    }

    public Zone getZoneById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Zone updateZone(Long id, Zone zone) {
        zone.setId(id);
        return repository.save(zone);
    }

    public void deleteZone(Long id) {
        repository.deleteById(id);
    }
}
