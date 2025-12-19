package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository, BinRepository binRepository) {
        this.repository = repository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getAvgDailyIncreaseWeekday() < 0 || model.getAvgDailyIncreaseWeekend() < 0)
            throw new RuntimeException("Increase cannot be negative");
        return repository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(long id, UsagePatternModel model) {
        UsagePatternModel existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Model not found"));
        existing.setAvgDailyIncreaseWeekday(model.getAvgDailyIncreaseWeekday());
        existing.setAvgDailyIncreaseWeekend(model.getAvgDailyIncreaseWeekend());
        existing.setLastUpdated(model.getLastUpdated());
        return repository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        return repository.findTopByBinOrderByLastUpdatedDesc(bin);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return repository.findAll();
    }
}
