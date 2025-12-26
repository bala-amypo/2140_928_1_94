package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
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
            .orElseThrow(() -> new RuntimeException("Bin not found"));
        
        FillLevelRecord latestRecord = recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
            .orElseThrow(() -> new RuntimeException("No fill records found"));
        
        UsagePatternModel model = modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
            .orElseThrow(() -> new RuntimeException("No usage model found"));
        
        // Simple prediction logic
        double remainingCapacity = 100 - latestRecord.getFillPercentage();
        double dailyIncrease = model.getAvgDailyIncreaseWeekday(); // Simplified
        int daysUntilFull = (int) Math.ceil(remainingCapacity / dailyIncrease);
        
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setModelUsed(model);
        prediction.setDaysUntilFull(daysUntilFull);
        prediction.setPredictedFullDate(LocalDate.now().plusDays(daysUntilFull));
        prediction.setGeneratedAt(LocalDateTime.now());
        
        return predictionRepository.save(prediction);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
            .orElseThrow(() -> new RuntimeException("Zone not found"));
        
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}