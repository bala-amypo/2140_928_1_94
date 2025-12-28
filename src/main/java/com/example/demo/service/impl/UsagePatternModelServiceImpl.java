package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
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
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + model.getBin().getId()));
        
        if (model.getAvgDailyIncreaseWeekday() == null || model.getAvgDailyIncreaseWeekday() < 0) {
            throw new BadRequestException("Weekday daily increase must be non-negative");
        }
        
        if (model.getAvgDailyIncreaseWeekend() == null || model.getAvgDailyIncreaseWeekend() < 0) {
            throw new BadRequestException("Weekend daily increase must be non-negative");
        }
        
        model.setBin(bin);
        model.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updates) {
        UsagePatternModel existing = modelRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Model not found with id: " + id));
        
        if (updates.getAvgDailyIncreaseWeekday() != null) {
            if (updates.getAvgDailyIncreaseWeekday() < 0) {
                throw new BadRequestException("Weekday daily increase must be non-negative");
            }
            existing.setAvgDailyIncreaseWeekday(updates.getAvgDailyIncreaseWeekday());
        }
        
        if (updates.getAvgDailyIncreaseWeekend() != null) {
            if (updates.getAvgDailyIncreaseWeekend() < 0) {
                throw new BadRequestException("Weekend daily increase must be non-negative");
            }
            existing.setAvgDailyIncreaseWeekend(updates.getAvgDailyIncreaseWeekend());
        }
        
        existing.setLastUpdated(LocalDateTime.now());
        return modelRepository.save(existing);
    }

    @Override
    @Transactional(readOnly = true)
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        
        return modelRepository.findTop1ByBinOrderByLastUpdatedDesc(bin)
            .orElseThrow(() -> new ResourceNotFoundException("No model found for bin with id: " + binId));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}