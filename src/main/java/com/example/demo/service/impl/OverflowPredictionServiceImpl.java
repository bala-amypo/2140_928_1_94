package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository overflowPredictionRepository;
    private final BinRepository binRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository overflowPredictionRepository,
                                         BinRepository binRepository) {
        this.overflowPredictionRepository = overflowPredictionRepository;
        this.binRepository = binRepository;
    }

    @Override
    public OverflowPrediction create(OverflowPrediction prediction) {
        return overflowPredictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return overflowPredictionRepository.findById(id).orElse(null);
    }

    @Override
    public List<OverflowPrediction> getAll() {
        return overflowPredictionRepository.findAll();
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        // Here you can set predictedLevel based on your logic or model
        return overflowPredictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getByBin(Long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        return overflowPredictionRepository.findByBin(bin);
    }

    @Override
    public OverflowPrediction getLatestForZone(Long zoneId) {
        Optional<OverflowPrediction> latest = overflowPredictionRepository
                .findTopByBin_Zone_IdOrderByPredictedAtDesc(zoneId);
        return latest.orElse(null);
    }
}
