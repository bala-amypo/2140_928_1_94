package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public BinServiceImpl(BinRepository binRepository,
                          ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    // -------------------- CREATE --------------------
    @Override
    public Bin create(Bin bin) {
        if (bin.getIdentifier() != null &&
                binRepository.findByIdentifier(bin.getIdentifier()).isPresent()) {
            throw new RuntimeException("Bin identifier already exists");
        }

        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                    .orElseThrow(() -> new RuntimeException("Zone not found"));
            bin.setZone(zone);
        }

        return binRepository.save(bin);
    }

    // -------------------- UPDATE --------------------
    @Override
    public Bin update(Long id, Bin updatedBin) {
        Bin bin = binRepository.findByIdWithZone(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        bin.setIdentifier(updatedBin.getIdentifier());
        bin.setLocationDescription(updatedBin.getLocationDescription());
        bin.setLatitude(updatedBin.getLatitude());
        bin.setLongitude(updatedBin.getLongitude());
        bin.setCapacityLiters(updatedBin.getCapacityLiters());
        bin.setActive(updatedBin.getActive());

        if (updatedBin.getZone() != null && updatedBin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(updatedBin.getZone().getId())
                    .orElseThrow(() -> new RuntimeException("Zone not found"));
            bin.setZone(zone);
        }

        return binRepository.save(bin);
    }

    // -------------------- GET BY ID --------------------
    @Override
    @Transactional(readOnly = true)
    public Bin getById(Long id) {
        return binRepository.findByIdWithZone(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
    }

    // -------------------- GET ALL --------------------
    @Override
    @Transactional(readOnly = true)
    public List<Bin> getAll() {
        return binRepository.findAllWithZone();
    }

    // -------------------- DEACTIVATE --------------------
    @Override
    public void deactivate(Long id) {
        Bin bin = binRepository.findByIdWithZone(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        bin.setActive(false);
        binRepository.save(bin);
    }
}
