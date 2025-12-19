package com.example.demo.service.impl;

import com.example.demo.model.Zone;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.ZoneService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ZoneServiceImpl implements ZoneService {

    private final ZoneRepository repository;

    public ZoneServiceImpl(ZoneRepository repository) {
        this.repository = repository;
    }

    @Override
    public Zone create(Zone zone) {
        return repository.save(zone);
    }

    @Override
    public Zone update(Long id, Zone zone) {
        Optional<Zone> existing = repository.findById(id);
        if (existing.isPresent()) {
            Zone z = existing.get();
            z.setName(zone.getName());
            z.setLocation(zone.getLocation());
            return repository.save(z);
        }
        return null;
    }

    @Override
    public Zone getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Zone> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Optional<Zone> existing = repository.findById(id);
        existing.ifPresent(z -> {
            z.setActive(false);
            repository.save(z);
        });
    }
}
`