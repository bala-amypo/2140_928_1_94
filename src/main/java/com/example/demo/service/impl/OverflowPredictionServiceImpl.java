package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
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
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        UsagePatternModel model = modelRepository.findTopByBinOrderByLastUpdatedDesc(bin)
                .orElseThrow(() -> new RuntimeException("Usage pattern model not found for bin"));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        // Simple prediction logic: calculate when bin will reach 100% using avg daily increase
        double dailyIncrease = LocalDateTime.now().getDayOfWeek().getValue() >= 6 ?
                model.getAvgDailyIncreaseWeekend() : model.getAvgDailyIncreaseWeekday();

        if (dailyIncrease <= 0) dailyIncrease = 1; // prevent division by zero

        int remainingCapacity = bin.getCapacityLiters(); // assuming fill starts from 0
        long daysToOverflow = (long) Math.ceil(100.0 / dailyIncrease);

        LocalDateTime predictedTime = LocalDateTime.now().plusDays(daysToOverflow);
        prediction.setPredictedOverflowTime(predictedTime);

        return predictionRepository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return predictionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Prediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        return predictionRepository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        Zone zone = new Zone();
        zone.setId(zoneId);
        return predictionRepository.findLatestPredictionsForZone(zone);
    }
}
