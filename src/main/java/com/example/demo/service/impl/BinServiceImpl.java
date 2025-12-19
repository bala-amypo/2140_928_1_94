package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BinServiceImpl implements BinService {

    private final BinRepository repository;

    public BinServiceImpl(BinRepository repository) {
        this.repository = repository;
    }

    @Override
    public Bin create(Bin bin) {
        return repository.save(bin);
    }

    @Override
    public Bin update(Long id, Bin bin) {
        Optional<Bin> existing = repository.findById(id);
        if (existing.isPresent()) {
            Bin b = existing.get();
            b.setName(bin.getName());
            b.setLocation(bin.getLocation());
            return repository.save(b);
        }
        return null;
    }

    @Override
    public Bin getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Bin> getAll() {
        return repository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Optional<Bin> existing = repository.findById(id);
        existing.ifPresent(b -> {
            b.setActive(false);
            repository.save(b);
        });
    }
}
