package com.example.demo.service.impl;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        return null;
    }

    @Override
    public OverflowPrediction getPredictionById(Long id) {
        return null;
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(Long binId) {
        return List.of();
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        return List.of();
    }
}
