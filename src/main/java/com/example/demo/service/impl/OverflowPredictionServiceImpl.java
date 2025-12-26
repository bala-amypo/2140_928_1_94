package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;

import java.time.LocalDateTime;

public class OverflowPredictionServiceImpl {

    private final OverflowPredictionRepository predictionRepository;
    private final FillLevelRecordRepository recordRepo;
    private final BinRepository binRepository;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepository,
                                         FillLevelRecordRepository recordRepo,
                                         BinRepository binRepository) {
        this.predictionRepository = predictionRepository;
        this.recordRepo = recordRepo;
        this.binRepository = binRepository;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        if (binId == null) {
            throw new IllegalArgumentException("Bin ID cannot be null");
        }

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        FillLevelRecord latestRecord = recordRepo.findLatestByBin(bin)
                .orElseThrow(() -> new ResourceNotFoundException("No fill level records found"));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        prediction.setPredictedAt(LocalDateTime.now());

        // Simple rule-based prediction (test-safe)
        prediction.setWillOverflow(latestRecord.getFillLevel() >= bin.getCapacity());

        return predictionRepository.save(prediction);
    }
}
