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

    @Override
    public Bin update(Long id, Bin updatedBin) {
        Bin bin = binRepository.findById(id)
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

    @Override
    @Transactional(readOnly = true)
    public Bin getById(Long id) {
        return binRepository.findByIdWithZone(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bin> getAll() {
        return binRepository.findAllWithZone();
    }

    @Override
    public void deactivate(Long id) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        bin.setActive(false);
        binRepository.save(bin);
    }
}
