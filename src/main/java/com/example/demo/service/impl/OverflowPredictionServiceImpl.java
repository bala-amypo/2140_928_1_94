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

    public OverflowPrediction create(OverflowPrediction prediction) {
        return repository.save(prediction);
    }

    public List<OverflowPrediction> getAll() {
        return repository.findAll();
    }

    public OverflowPrediction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public OverflowPrediction update(Long id, OverflowPrediction prediction) {
        prediction.setId(id);
        return repository.save(prediction);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
