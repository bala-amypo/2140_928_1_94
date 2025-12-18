package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service   // 🔴 THIS ANNOTATION IS REQUIRED
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    // REQUIRED constructor order
    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() <= 0) {
            throw new IllegalArgumentException("capacity must be greater than zero");
        }
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(long id, Bin bin) {
        Bin existing = getBinById(id);
        existing.setIdentifier(bin.getIdentifier());
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setLatitude(bin.getLatitude());
        existing.setLongitude(bin.getLongitude());
        existing.setCapacityLiters(bin.getCapacityLiters());
        return binRepository.save(existing);
    }

    @Override
    public Bin getBinById(long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("bin not found"));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
