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
    public Zone save(Zone zone) {
        return zoneRepository.save(zone);
    }

    @Override
    public List<Zone> findAll() {
        return zoneRepository.findAll();
    }

    @Override
    public Zone findById(Long id) {
        return zoneRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        zoneRepository.deleteById(id);
    }
}
