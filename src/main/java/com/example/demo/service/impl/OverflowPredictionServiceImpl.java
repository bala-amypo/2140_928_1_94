package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository repository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository repository) {
        this.repository = repository;
    }

    @Override
    public OverflowPrediction create(OverflowPrediction prediction) {
        return repository.save(prediction);
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("OverflowPrediction not found"));
    }

    @Override
    public List<OverflowPrediction> getAll() {
        return repository.findAll();
    }
}
