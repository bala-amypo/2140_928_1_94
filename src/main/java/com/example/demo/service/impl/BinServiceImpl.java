package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    public Bin createBin(Bin bin) {
        bin.setActive(true);
        bin.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(bin);
    }

    public Bin getBinById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    public Bin updateBin(Long id, Bin bin) {
        Bin existing = getBinById(id);
        existing.setIdentifier(bin.getIdentifier());
        existing.setLocationDescription(bin.getLocationDescription());
        existing.setLatitude(bin.getLatitude());
        existing.setLongitude(bin.getLongitude());
        existing.setZone(bin.getZone());
        existing.setCapacityLiters(bin.getCapacityLiters());
        existing.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        return binRepository.save(existing);
    }

    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
