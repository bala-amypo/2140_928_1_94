package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository fillRecordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                         FillLevelRecordRepository fillRecordRepository,
                                         UsagePatternModelRepository modelRepository,
                                         OverflowPredictionRepository predictionRepository,
                                         ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.fillRecordRepository = fillRecordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        UsagePatternModel model = modelRepository.findTopByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new ResourceNotFoundException("UsagePatternModel not found for bin"));

        // Simple linear prediction based on last known fill + avg increase
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        // Here we use a very simple example: assume average increase for weekday/weekend
        LocalDateTime now = LocalDateTime.now();
        int avgIncrease = model.getAvgDailyIncreaseWeekday() > 0 ? (int) model.getAvgDailyIncreaseWeekday() : 1;

        // For simplicity, assume predicted overflow time is now + (capacity / avgIncrease) days
        double daysToOverflow = (double) bin.getCapacityLiters() / avgIncrease;
        prediction.setPredictedOverflowTime(now.plusHours((long) (daysToOverflow * 24)));
        prediction.setCreatedAt(LocalDateTime.now());

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OverflowPrediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return predictionRepository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        var zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
