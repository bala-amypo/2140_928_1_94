package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository,
                          ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin create(Bin bin) {
        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Zone not found with id " + bin.getZone().getId()));

        bin.setZone(zone);
        return binRepository.save(bin);
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin getById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Bin not found with id " + id));
    }

    @Override
    public void deactivate(Long id) {
        Bin bin = getById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
