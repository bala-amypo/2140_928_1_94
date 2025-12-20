package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public Bin createBin(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Optional<Bin> getBinById(Long id) {
        return binRepository.findById(id);
    }

    @Override
    public List<Bin> getAllBins() {
        return binRepository.findAll();
    }

    @Override
    public Bin updateBin(Long id, Bin bin) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + id));

        existingBin.setName(bin.getName());
        existingBin.setZone(bin.getZone());
        existingBin.setCapacity(bin.getCapacity());
        existingBin.setActive(bin.isActive());

        return binRepository.save(existingBin);
    }

    @Override
    public Bin deactivateBin(Long id) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + id));

        existingBin.setActive(false);
        return binRepository.save(existingBin);
    }
}
