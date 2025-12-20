package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository, BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        Long binId = model.getBin().getId();
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + binId));
        model.setBin(bin);
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with id " + id));
        existing.setModelData(model.getModelData());
        existing.setBin(existing.getBin());
        return modelRepository.save(existing);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id " + binId));
        return modelRepository.findByBin(bin)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found for bin id " + binId));
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}
