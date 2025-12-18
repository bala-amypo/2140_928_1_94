package com.example.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository repository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public OverflowPrediction create(OverflowPrediction data) {
        return repository.save(data);
    }

    @Override
    public List<OverflowPrediction> getAll() {
        return repository.findAll();
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public OverflowPrediction update(Long id, OverflowPrediction data) {
        data.setId(id);
        return repository.save(data);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
