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

    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository modelRepository,
            BinRepository binRepository
    ) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    // ✅ matches: createModel(...)
    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {

        if (model.getBin() != null && model.getBin().getId() != null) {
            Bin bin = binRepository.findById(model.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            model.setBin(bin);
        }

        return modelRepository.save(model);
    }

    // ✅ matches: getAllModels()
    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }

    // ✅ matches: updateModel(...)
    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel) {

        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsagePatternModel not found"));

        if (updatedModel.getBin() != null && updatedModel.getBin().getId() != null) {
            Bin bin = binRepository.findById(updatedModel.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            existing.setBin(bin);
        }

        return modelRepository.save(existing);
    }

    // ✅ matches: getModelForBin(...)
    @Override
    public UsagePatternModel getModelForBin(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        List<UsagePatternModel> models = modelRepository.findByBin(bin);

        if (models.isEmpty()) {
            throw new RuntimeException("No UsagePatternModel found for bin");
        }

        return models.get(0);
    }
}
