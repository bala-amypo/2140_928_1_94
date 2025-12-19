package com.example.demo.service;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction generatePrediction(long binId);

    OverflowPrediction getPredictionById(long id);

    List<OverflowPrediction> getPredictionsForBin(long binId);

    List<OverflowPrediction> getLatestPredictionsForZone(long zoneId);
}
