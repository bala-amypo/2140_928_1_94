package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Autowired
    private OverflowPredictionRepository overflowPredictionRepository;

    @Autowired
    private BinRepository binRepository;

    @Override
    public OverflowPrediction create(OverflowPrediction prediction) {
        return overflowPredictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getById(Long id) {
        return overflowPredictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OverflowPrediction not found with id " + id));
    }

    @Override
    public List<OverflowPrediction> getAll() {
        return overflowPredictionRepository.findAll();
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + binId));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setPredictedLevel(0.0); // placeholder logic, replace with real prediction

        return overflowPredictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getByBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + binId));
        return overflowPredictionRepository.findByBin(bin);
    }

    @Override
    public OverflowPrediction getLatestForZone(Long zoneId) {
        List<OverflowPrediction> predictions = overflowPredictionRepository.findByBin_Zone_Id(zoneId);
        return predictions.isEmpty() ? null : predictions.get(predictions.size() - 1); // latest
    }
}
