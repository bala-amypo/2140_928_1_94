package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import java.util.List;

public interface OverflowPredictionService {
    OverflowPrediction generatePrediction(Long binId);
    List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId);
}