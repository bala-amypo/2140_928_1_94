package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.OverflowPredictionService;

import java.util.Optional;

public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final OverflowPredictionRepository predictionRepo;
    private final FillLevelRecordRepository recordRepo;

    public OverflowPredictionServiceImpl(OverflowPredictionRepository predictionRepo,
                                         FillLevelRecordRepository recordRepo) {
        this.predictionRepo = predictionRepo;
        this.recordRepo = recordRepo;
    }

    @Override
    public OverflowPrediction predictOverflow(Bin bin) {
        Optional<FillLevelRecord> latestRecordOpt = recordRepo.findTop1ByBinOrderByRecordedAtDesc(bin);
        if (latestRecordOpt.isEmpty()) return null;

        FillLevelRecord latestRecord = latestRecordOpt.get();

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        // If your model has a predictedAt field, use:
        // prediction.setPredictedAt(LocalDateTime.now());
        prediction.setPredictedLevel(latestRecord.getFillLevel()); // example logic

        return predictionRepo.save(prediction);
    }
}
