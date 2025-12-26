package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;

public class ZoneServiceImpl {

    private final ZoneRepository repo;

    public ZoneServiceImpl(ZoneRepository repo) {
        this.repo = repo;
    }

    public Zone createZone(Zone z) {
        z.setActive(true);
        return repo.save(z);
    }

    public Zone getZoneById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
    }

    public Zone updateZone(Long id, Zone update) {
        Zone z = getZoneById(id);
        if (update.getDescription() != null) z.setDescription(update.getDescription());
        return repo.save(z);
    }

    public void deactivateZone(Long id) {
        Zone z = getZoneById(id);
        z.setActive(false);
        repo.save(z);
    }
}
