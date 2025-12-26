package com.example.demo.service.impl;

import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDate;
import java.util.List;

public class OverflowPredictionServiceImpl {

    private final BinRepository binRepo;
    private final FillLevelRecordRepository recordRepo;
    private final UsagePatternModelRepository modelRepo;
    private final OverflowPredictionRepository predictionRepo;
    private final ZoneRepository zoneRepo;

    public OverflowPredictionServiceImpl(
            BinRepository b, FillLevelRecordRepository r,
            UsagePatternModelRepository m,
            OverflowPredictionRepository p,
            ZoneRepository z) {
        binRepo = b;
        recordRepo = r;
        modelRepo = m;
        predictionRepo = p;
        zoneRepo = z;
    }

    public OverflowPrediction generatePrediction(Long binId) {
        Bin bin = binRepo.findById(binId).orElseThrow();
        FillLevelRecord record = recordRepo.findTop1ByBinOrderByRecordedAtDesc(bin).orElseThrow();
        UsagePatternModel model = modelRepo.findTop1ByBinOrderByLastUpdatedDesc(bin).orElseThrow();

        OverflowPrediction p = new OverflowPrediction();
        p.setBin(bin);
        p.setModelUsed(model);
        p.setDaysUntilFull(3);
        p.setPredictedFullDate(LocalDate.now().plusDays(3));
        return predictionRepo.save(p);
    }

    public List<OverflowPrediction> getLatestPredictionsForZone(Long zoneId) {
        Zone z = zoneRepo.findById(zoneId).orElseThrow();
        return predictionRepo.findLatestPredictionsForZone(z);
    }
}
