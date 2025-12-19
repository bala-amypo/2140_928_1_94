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
    public OverflowPrediction generate(Long binId) {
        // simple stub, in real app, add prediction logic
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBinId(binId);
        return repository.save(prediction);
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<OverflowPrediction> getByBin(Long binId) {
        return repository.findByBinId(binId);
    }

    @Override
    public List<OverflowPrediction> getLatestByZone(Long zoneId) {
        return repository.findLatestByZoneId(zoneId);
    }
}
