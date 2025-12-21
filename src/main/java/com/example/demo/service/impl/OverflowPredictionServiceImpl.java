package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final BinRepository binRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepository,
                                         BinRepository binRepository) {
        this.predictionRepository = predictionRepository;
        this.binRepository = binRepository;
    }

    @Override
    public OverflowPrediction create(OverflowPrediction prediction) {
        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OverflowPrediction not found with id: " + id));
    }

    @Override
    public List<OverflowPrediction> getAll() {
        return predictionRepository.findAll();
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setPredictedLevel(0.0); // Replace with actual prediction logic
        return predictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getByBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        return predictionRepository.findByBin(bin);
    }

    @Override
    public OverflowPrediction getLatestForZone(Long zoneId) {
        return predictionRepository.findTopByBin_Zone_IdOrderByPredictedAtDesc(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("No predictions found for zone id: " + zoneId));
    }
}
