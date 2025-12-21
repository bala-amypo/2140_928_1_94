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
    public UsagePatternModel create(UsagePatternModel model) {

        if (model.getBin() != null && model.getBin().getId() != null) {
            Bin bin = binRepository.findById(model.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            model.setBin(bin);
        }

        return modelRepository.save(model);
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return modelRepository.findAll();
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel) {

        UsagePatternModel model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UsagePatternModel not found"));

        // DO NOT TOUCH FIELD NAMES — copy whole object safely
        model.setBin(updatedModel.getBin());

        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        List<UsagePatternModel> models = modelRepository.findByBin(bin);

        if (models.isEmpty()) {
            throw new RuntimeException("No model found for bin");
        }

        return models.get(0); // interface expects ONE model
    }
}
