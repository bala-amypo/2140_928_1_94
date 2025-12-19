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
    public Bin update(Long id, Bin bin) {
        bin.setId(id);
        return binRepository.save(bin);
    }

    @Override
    public Bin getById(Long id) {
        return binRepository.findById(id).orElse(null);
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public void delete(Long id) {
        binRepository.deleteById(id);
    }

    @Override
    public Bin deactivate(Long id) {
        Bin bin = binRepository.findById(id).orElse(null);
        if (bin != null) {
            bin.setActive(false);
            return binRepository.save(bin);
        }
        return null;
    }
}
