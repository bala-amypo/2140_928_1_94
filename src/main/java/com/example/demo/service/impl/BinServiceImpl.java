package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

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
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be positive");
        }
        
        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
            
            if (!zone.getActive()) {
                throw new BadRequestException("Cannot add bin to inactive zone");
            }
            bin.setZone(zone);
        }
        
        return binRepository.save(bin);
    }

    @Override
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
    }

    @Override
    public Bin updateBin(Long id, Bin updates) {
        Bin existing = getBinById(id);
        
        if (updates.getLocationDescription() != null) {
            existing.setLocationDescription(updates.getLocationDescription());
        }
        // Add more update fields as needed
        
        return binRepository.save(existing);
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }
}