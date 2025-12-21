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

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository modelRepository,
                                        BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        if (model.getBin() == null || model.getBin().getId() == null) {
            throw new RuntimeException("Bin must be provided");
        }

        // Fetch Bin from DB
        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        model.setBin(bin);

        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel) {
        UsagePatternModel existingModel = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsagePatternModel not found"));

        existingModel.setAverageDailyUsage(updatedModel.getAverageDailyUsage());
        existingModel.setPeakUsage(updatedModel.getPeakUsage());
        existingModel.setPatternType(updatedModel.getPatternType());

        if (updatedModel.getBin() != null && updatedModel.getBin().getId() != null) {
            Bin bin = binRepository.findById(updatedModel.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            existingModel.setBin(bin);
        }

        return modelRepository.save(existingModel);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        return modelRepository.findByBin(bin)
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("UsagePatternModel for Bin not found"));
    }
}