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
    public Bin save(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public List<Bin> findAll() {
        return binRepository.findAll();
    }

    @Override
    public Bin findById(Long id) {
        return binRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        binRepository.deleteById(id);
    }
}
