package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + model.getBin().getId()));
        model.setBin(bin);
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        UsagePatternModel existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Model not found with id: " + id));
        existingModel.setPatternData(model.getPatternData());
        return modelRepository.save(existingModel);
    }

    @Override
    public Optional<UsagePatternModel> getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        return modelRepository.findByBin(bin);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }
}
