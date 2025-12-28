package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
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

    public BinServiceImpl(BinRepository binRepository, ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        // Validate required fields
        if (bin.getIdentifier() == null || bin.getIdentifier().trim().isEmpty()) {
            throw new BadRequestException("Bin identifier is required");
        }
        
        if (bin.getCapacityLiters() == null || bin.getCapacityLiters() <= 0) {
            throw new BadRequestException("Bin capacity must be positive");
        }
        
        // Validate coordinates if provided
        if (bin.getLatitude() != null && (bin.getLatitude() < -90 || bin.getLatitude() > 90)) {
            throw new BadRequestException("Latitude must be between -90 and 90");
        }
        
        if (bin.getLongitude() != null && (bin.getLongitude() < -180 || bin.getLongitude() > 180)) {
            throw new BadRequestException("Longitude must be between -180 and 180");
        }
        
        // Validate zone if provided
        if (bin.getZone() != null && bin.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(bin.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + bin.getZone().getId()));
            
            if (!zone.getActive()) {
                throw new BadRequestException("Cannot add bin to inactive zone");
            }
            bin.setZone(zone);
        }
        
        // Set default active status if not provided
        if (bin.getActive() == null) {
            bin.setActive(true);
        }
        
        // Check for duplicate identifier
        binRepository.findByIdentifier(bin.getIdentifier())
            .ifPresent(existing -> {
                throw new BadRequestException("Bin with identifier '" + bin.getIdentifier() + "' already exists");
            });
        
        return binRepository.save(bin);
    }

    @Override
    @Transactional(readOnly = true)
    public Bin getBinById(Long id) {
        return binRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + id));
    }

    @Override
    public Bin updateBin(Long id, Bin updates) {
        Bin existing = getBinById(id);
        
        // Only update non-null fields
        if (updates.getLocationDescription() != null) {
            existing.setLocationDescription(updates.getLocationDescription());
        }
        
        if (updates.getLatitude() != null) {
            if (updates.getLatitude() < -90 || updates.getLatitude() > 90) {
                throw new BadRequestException("Latitude must be between -90 and 90");
            }
            existing.setLatitude(updates.getLatitude());
        }
        
        if (updates.getLongitude() != null) {
            if (updates.getLongitude() < -180 || updates.getLongitude() > 180) {
                throw new BadRequestException("Longitude must be between -180 and 180");
            }
            existing.setLongitude(updates.getLongitude());
        }
        
        if (updates.getCapacityLiters() != null) {
            if (updates.getCapacityLiters() <= 0) {
                throw new BadRequestException("Bin capacity must be positive");
            }
            existing.setCapacityLiters(updates.getCapacityLiters());
        }
        
        // Update zone if provided
        if (updates.getZone() != null && updates.getZone().getId() != null) {
            Zone zone = zoneRepository.findById(updates.getZone().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
            
            if (!zone.getActive()) {
                throw new BadRequestException("Cannot move bin to inactive zone");
            }
            existing.setZone(zone);
        }
        
        // Update active status if provided
        if (updates.getActive() != null) {
            existing.setActive(updates.getActive());
        }
        
        return binRepository.save(existing);
    }

    @Override
    public void deactivateBin(Long id) {
        Bin bin = getBinById(id);
        
        if (!bin.getActive()) {
            throw new BadRequestException("Bin is already deactivated");
        }
        
        bin.setActive(false);
        binRepository.save(bin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }
    
    // Additional method to reactivate bin
    public void reactivateBin(Long id) {
        Bin bin = getBinById(id);
        
        if (bin.getActive()) {
            throw new BadRequestException("Bin is already active");
        }
        
        // Check if zone is active before reactivating
        if (bin.getZone() != null && !bin.getZone().getActive()) {
            throw new BadRequestException("Cannot reactivate bin in inactive zone");
        }
        
        bin.setActive(true);
        binRepository.save(bin);
    }
    
    // Additional method to get active bins
    public List<Bin> getActiveBins() {
        return binRepository.findAll().stream()
            .filter(Bin::getActive)
            .toList();
    }
    
    // Additional method to get bins by zone
    public List<Bin> getBinsByZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        
        return binRepository.findByZoneAndActiveTrue(zone);
    }
}