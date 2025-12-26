package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;

import java.time.LocalDateTime;
import java.util.Optional;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final FillLevelRecordRepository recordRepo;
    private final OverflowPredictionRepository predictionRepo;

    public OverflowPredictionServiceImpl(FillLevelRecordRepository recordRepo, OverflowPredictionRepository predictionRepo) {
        this.recordRepo = recordRepo;
        this.predictionRepo = predictionRepo;
    }

    @Override
    public OverflowPrediction predictOverflow(Bin bin) {
        Optional<FillLevelRecord> latestRecordOpt = recordRepo.findTop1ByBinOrderByRecordedAtDesc(bin);
        if (latestRecordOpt.isEmpty()) return null;

        FillLevelRecord latestRecord = latestRecordOpt.get();
        OverflowPrediction prediction = new OverflowPrediction();

        prediction.setPredictedAt(LocalDateTime.now());
        prediction.setBin(bin);

        if (latestRecord.getFillLevel() != null && bin.getCapacityLiters() != null) {
            double fillPercentage = (double) latestRecord.getFillLevel() / bin.getCapacityLiters() * 100;
            prediction.setFillPercentage(fillPercentage);
        }

        return predictionRepo.save(prediction);
    }
}
