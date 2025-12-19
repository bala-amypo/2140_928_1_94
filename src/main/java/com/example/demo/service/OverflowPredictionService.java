package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import java.util.List;

public interface OverflowPredictionService {
    OverflowPrediction generate(Long binId);
    OverflowPrediction getById(Long id);
    List<OverflowPrediction> getByBin(Long binId);
    List<OverflowPrediction> getLatestByZone(Long zoneId);
}
