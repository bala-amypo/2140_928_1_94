package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {
    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, 
                                       BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        Bin bin = binRepository.findById(model.getBin().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        if (model.getAvgDailyIncreaseWeekday() < 0 || 
            model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Daily increase values must be non-negative");
        }
        
        model.setBin(bin);
        model.setLastUpdated(java.time.LocalDateTime.now());
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updates) {
        UsagePatternModel existing = modelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Model not found"));
        
        if (updates.getAvgDailyIncreaseWeekday() != null) {
            existing.setAvgDailyIncreaseWeekday(updates.getAvgDailyIncreaseWeekday());
        }
        
        if (updates.getAvgDailyIncreaseWeekend() != null) {
            existing.setAvgDailyIncreaseWeekend(updates.getAvgDailyIncreaseWeekend());
        }
        
        existing.setLastUpdated(java.time.LocalDateTime.now());
        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
            .orElseThrow(() -> new ResourceNotFoundException("No model found for bin"));
    }
}