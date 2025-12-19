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
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBinId(binId);
        // Basic stub, just save empty prediction
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
        return repository.findTopByZoneIdOrderByTimestampDesc(zoneId);
    }
}
