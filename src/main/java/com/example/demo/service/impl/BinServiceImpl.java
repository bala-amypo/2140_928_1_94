package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public Bin createBin(Bin bin) {
        if (bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be greater than 0");
        }
        bin.setActive(true);
        bin.setCreatedAt(LocalDateTime.now());
        bin.setUpdatedAt(LocalDateTime.now());
        return binRepository.save(bin);
    }

    @Override
    public Bin updateBin(long id, Bin binDetails) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        bin.setIdentifier(binDetails.getIdentifier());
        bin.setLocationDescription(binDetails.getLocationDescription());
        bin.setLatitude(binDetails.getLatitude());
        bin.setLongitude(binDetails.getLongitude());
        bin.setCapacityLiters(binDetails.getCapacityLiters());
        bin.setZone(binDetails.getZone());
        bin.setUpdatedAt(LocalDateTime.now());
        return binRepository.save(bin);
    }

    @Override
    public Bin getBinById(long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public void deactivateBin(long id) {
        Bin bin = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        bin.setActive(false);
        bin.setUpdatedAt(LocalDateTime.now());
        binRepository.save(bin);
    }
}
