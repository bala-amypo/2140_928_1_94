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

    public Zone create(Zone zone) {
        return repository.save(zone);
    }

    public List<Zone> getAll() {
        return repository.findAll();
    }

    public Zone getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Zone update(Long id, Zone zone) {
        zone.setId(id);
        return repository.save(zone);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
