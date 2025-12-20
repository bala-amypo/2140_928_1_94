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
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + id));
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin update(Long id, Bin bin) {
        Bin existing = getById(id);
        existing.setLocation(bin.getLocation());
        existing.setCapacity(bin.getCapacity());
        existing.setActive(bin.isActive());
        return binRepository.save(existing);
    }

    @Override
    public void deactivate(Long id) {
        Bin bin = getById(id);
        bin.setActive(false);
        binRepository.save(bin);
    }
}
