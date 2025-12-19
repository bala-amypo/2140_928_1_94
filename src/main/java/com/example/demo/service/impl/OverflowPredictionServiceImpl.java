package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepository;
    private final BinRepository binRepository;
    private final UsagePatternModelRepository modelRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepository,
                                         BinRepository binRepository,
                                         UsagePatternModelRepository modelRepository) {
        this.predictionRepository = predictionRepository;
        this.binRepository = binRepository;
        this.modelRepository = modelRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        UsagePatternModel model = modelRepository.findTopByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for bin"));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        double dailyIncrease = model.getAvgDailyIncreaseWeekday(); // simplify for demo
        double remainingCapacity = bin.getCapacityLiters() - 0; // assume current level 0
        long hoursToOverflow = (long) (remainingCapacity / dailyIncrease);

        prediction.setPredictedOverflowTime(LocalDateTime.now().plusHours(hoursToOverflow));
        prediction.setCreatedAt(LocalDateTime.now());

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        Zone zone = new Zone();
        zone.setId(zoneId);
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
