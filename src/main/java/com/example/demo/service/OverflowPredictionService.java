package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import java.util.List;

public interface OverflowPredictionService {
    OverflowPrediction generatePrediction(long binId);
    OverflowPrediction getPredictionById(long id);
    List<OverflowPrediction> getPredictionsForBin(long binId);
    List<OverflowPrediction> getLatestPredictionsForZone(long zoneId);
}
