package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;

import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction create(OverflowPrediction prediction);

    OverflowPrediction getById(Long id);

    List<OverflowPrediction> getAll();

    // New required endpoints
    OverflowPrediction generatePrediction(Long binId);

    List<OverflowPrediction> getByBin(Long binId);

    OverflowPrediction getLatestForZone(Long zoneId);
}
