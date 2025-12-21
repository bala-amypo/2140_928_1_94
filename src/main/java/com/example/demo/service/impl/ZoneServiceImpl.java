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

    // ✅ FIX 1: force persist, never merge
    @Override
    public Zone create(Zone zone) {
        zone.setId(null);          // CRITICAL
        if (zone.getActive() == null) {
            zone.setActive(true);  // safe default
        }
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

    // ✅ FIX 2: update managed entity ONLY
    @Override
    public Zone update(Long id, Zone zone) {
        Zone existing = getById(id);

        existing.setZoneName(zone.getZoneName());
        existing.setDescription(zone.getDescription());
        existing.setActive(zone.getActive());

        return zoneRepository.save(existing);
    }

    // ✅ FIX 3: deactivate actually works
    @Override
    public void deactivate(Long id) {
        Zone zone = getById(id);
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}
