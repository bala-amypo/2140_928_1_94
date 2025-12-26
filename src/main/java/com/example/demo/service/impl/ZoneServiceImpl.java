package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ZoneServiceImpl implements ZoneService {
    private final ZoneRepository zoneRepository;

    public ZoneServiceImpl(ZoneRepository zoneRepository) {
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Zone createZone(Zone zone) {
        zone.setActive(true);
        
        if (zone.getZoneName() != null && !zone.getZoneName().trim().isEmpty()) {
            zoneRepository.findByZoneName(zone.getZoneName())
                .ifPresent(existing -> {
                    throw new BadRequestException("Zone with name '" + zone.getZoneName() + "' already exists");
                });
        }
        
        return zoneRepository.save(zone);
    }

    @Override
    @Transactional(readOnly = true)
    public Zone getZoneById(Long id) {
        return zoneRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + id));
    }

    @Override
    public Zone updateZone(Long id, Zone updates) {
        Zone existing = getZoneById(id);
        
        if (updates.getZoneName() != null && !updates.getZoneName().equals(existing.getZoneName())) {
            zoneRepository.findByZoneName(updates.getZoneName())
                .ifPresent(zoneWithSameName -> {
                    if (!zoneWithSameName.getId().equals(id)) {
                        throw new BadRequestException("Zone with name '" + updates.getZoneName() + "' already exists");
                    }
                });
            existing.setZoneName(updates.getZoneName());
        }
        
        if (updates.getDescription() != null) {
            existing.setDescription(updates.getDescription());
        }
        
        return zoneRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Zone> getAllZones() {
        return zoneRepository.findAll();
    }

    @Override
    public void deactivateZone(Long id) {
        Zone zone = getZoneById(id);
        
        if (!zone.getActive()) {
            throw new BadRequestException("Zone is already deactivated");
        }
        
        zone.setActive(false);
        zoneRepository.save(zone);
    }
}