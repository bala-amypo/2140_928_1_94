package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository binRepository;

    public BinServiceImpl(BinRepository binRepository) {
        this.binRepository = binRepository;
    }

    @Override
    public Bin create(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin getById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin update(Long id, Bin bin) {
        // We only ensure the bin exists
        getById(id);
        return binRepository.save(bin);
    }

    @Override
    public void deactivate(Long id) {
        // Interface requires it, but model has no "active" field
        // So this is intentionally empty
    }
}
