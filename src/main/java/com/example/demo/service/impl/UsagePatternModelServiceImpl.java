package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.model.*;
import com.example.demo.repository.*;

public class UsagePatternModelServiceImpl {

    private final UsagePatternModelRepository repo;
    private final BinRepository binRepo;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository r, BinRepository b) {
        repo = r;
        binRepo = b;
    }

    public UsagePatternModel createModel(UsagePatternModel m) {
        Bin bin = binRepo.findById(m.getBin().getId()).orElseThrow();

        if (m.getAvgDailyIncreaseWeekday() < 0 || m.getAvgDailyIncreaseWeekend() < 0)
            throw new BadRequestException("Invalid increase");

        return repo.save(m);
    }

    public UsagePatternModel updateModel(Long id, UsagePatternModel update) {
        UsagePatternModel m = repo.findById(id).orElseThrow();
        if (update.getAvgDailyIncreaseWeekend() != null)
            m.setAvgDailyIncreaseWeekend(update.getAvgDailyIncreaseWeekend());
        return m;
    }

    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepo.findById(binId).orElseThrow();
        return repo.findTop1ByBinOrderByLastUpdatedDesc(bin).orElseThrow();
    }
}
