package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.repository.ZoneRepository;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository fillLevelRecordRepository;
    private final UsagePatternModelRepository usagePatternModelRepository;
    private final OverflowPredictionRepository overflowPredictionRepository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(
            BinRepository binRepository,
            FillLevelRecordRepository fillLevelRecordRepository,
            UsagePatternModelRepository usagePatternModelRepository,
            OverflowPredictionRepository overflowPredictionRepository,
            ZoneRepository zoneRepository
    ) {
        this.binRepository = binRepository;
        this.fillLevelRecordRepository = fillLevelRecordRepository;
        this.usagePatternModelRepository = usagePatternModelRepository;
        this.overflowPredictionRepository = overflowPredictionRepository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        // Example logic for predictedOverflowTime (simple placeholder)
        prediction.setPredictedOverflowTime(LocalDateTime.now().plusDays(1));

        overflowPredictionRepository.save(prediction);
        return prediction;
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return overflowPredictionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OverflowPrediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return overflowPredictionRepository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        Zone zone = zoneRepository.findById(zoneId)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found"));
        return overflowPredictionRepository.findLatestPredictionsForZone(zone);
    }
}
