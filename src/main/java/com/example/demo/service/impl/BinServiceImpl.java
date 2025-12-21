package com.example.demo.service.impl;

import com.example.demo.entity.Bin;
import com.example.demo.entity.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin create(Bin bin) {

        if (bin.getIdentifier() == null || bin.getIdentifier().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Identifier is required");
        }

        if (binRepository.existsByIdentifier(bin.getIdentifier())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bin identifier already exists");
        }

        if (bin.getZone() == null || bin.getZone().getId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zone id is required");
        }

        Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Zone not found with id " + bin.getZone().getId()));

        bin.setZone(zone);
        bin.setActive(bin.getActive() != null ? bin.getActive() : true);

        return binRepository.save(bin);
    }

    @Override
    public Bin update(Long id, Bin bin) {
        Bin existing = getById(id);

        existing.setIdentifier(bin.getIdentifier());
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setLatitude(bin.getLatitude());
        existing.setLongitude(bin.getLongitude());
        existing.setCapacityLiters(bin.getCapacityLiters());
        existing.setActive(bin.getActive());

        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                            "Zone not found with id " + bin.getZone().getId()));
            existing.setZone(zone);
        }

        return binRepository.save(existing);
    }

    @Override
    public Bin getById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Bin not found with id " + id));
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Bin bin = getById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
