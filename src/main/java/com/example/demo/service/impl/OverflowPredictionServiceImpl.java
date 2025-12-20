package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final BinRepository binRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepository, 
                                         BinRepository binRepository,
                                         ZoneRepository zoneRepository) {
        this.predictionRepository = predictionRepository;
        this.binRepository = binRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setPredictedLevel(0.75); // Example logic, replace with real model
        return predictionRepository.save(prediction);
    }

    @Override
    public Optional<OverflowPrediction> getPredictionById(Long id) {
        return predictionRepository.findById(id);
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        return predictionRepository.findByBinOrderByCreatedAtDesc(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + zoneId));
        return predictionRepository.findLatestByZone(zone);
    }
}
