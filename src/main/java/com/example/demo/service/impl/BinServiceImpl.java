package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository repository;

    public BinServiceImpl(BinRepository repository) {
        this.repository = repository;
    }

    public Bin create(Bin bin) {
        return repository.save(bin);
    }

    public List<Bin> getAll() {
        return repository.findAll();
    }

    public Bin getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public Bin update(Long id, Bin bin) {
        bin.setId(id);
        return repository.save(bin);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
