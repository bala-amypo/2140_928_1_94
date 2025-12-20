package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone create(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public Zone getById(Long id) {
        return zoneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Zone not found with id " + id));
    }

    @Override
    public List<Zone> getAll() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone update(Long id, Zone zone) {
        Zone existing = getById(id);
        existing.setName(zone.getName());
        existing.setActive(zone.isActive());
        return zoneRepository.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        Zone zone = getById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
