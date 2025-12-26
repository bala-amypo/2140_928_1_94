package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class OverflowPredictionServiceImpl implements OverflowPredictionService {
    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository predictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                        FillLevelRecordRepository recordRepository,
                                        UsagePatternModelRepository modelRepository,
                                        OverflowPredictionRepository predictionRepository,
                                        ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.predictionRepository = predictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        
        if (!bin.getActive()) {
            throw new ResourceNotFoundException("Cannot generate prediction for inactive bin");
        }
        
        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
            .orElseThrow(() -> new ResourceNotFoundException("No fill records found for bin with id: " + binId));
        
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
            .orElseThrow(() -> new ResourceNotFoundException("No usage model found for bin with id: " + binId));
        
        // Simple prediction logic
        double remainingCapacity = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = model.getAvgDailyIncreaseWeekday(); // Simplified - could use weekday/weekend logic
        int daysUntilFull = dailyIncrease > 0 ? (int) Math.ceil(remainingCapacity / dailyIncrease) : Integer.MAX_VALUE;
        
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));
        prediction.setGeneratedAt(LocalDateTime.now());
        
        return predictionRepository.save(prediction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id: " + zoneId));
        
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
    
    // Additional method to get prediction for a specific bin
    @Transactional(readOnly = true)
    public OverflowPrediction getLatestPredictionForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        List<OverflowPrediction> predictions = predictionRepository.findAll().stream()
            .filter(p -> p.getBin().getId().equals(binId))
            .sorted((p1, p2) -> p2.getGeneratedAt().compareTo(p1.getGeneratedAt()))
            .toList();
        
        if (predictions.isEmpty()) {
            throw new ResourceNotFoundException("No predictions found for bin");
        }
        
        return predictions.get(0);
    }
    
    // Additional method to generate predictions for all active bins in a zone
    public List<OverflowPrediction> generatePredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        
        List<Bin> activeBins = binRepository.findByZoneAndActiveTrue(zone);
        
        return activeBins.stream()
            .map(bin -> {
                try {
                    return generatePrediction(bin.getId());
                } catch (Exception e) {
                    // Log error but continue with other bins
                    System.err.println("Failed to generate prediction for bin " + bin.getId() + ": " + e.getMessage());
                    return null;
                }
            })
            .filter(prediction -> prediction != null)
            .toList();
    }
}