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
        } else {
            throw new RuntimeException("Bin is required");
        }
        return modelRepository.save(model);
    }

    @Override
    public UsagePatternModel update(Long id, UsagePatternModel updatedModel) {
        UsagePatternModel model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));

        model.setAverageDailyUsage(updatedModel.getAverageDailyUsage());
        model.setPeakUsage(updatedModel.getPeakUsage());
        model.setPatternType(updatedModel.getPatternType());

        if (updatedModel.getBin() != null && updatedModel.getBin().getId() != null) {
            Bin bin = binRepository.findById(updatedModel.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            model.setBin(bin);
        }

        return modelRepository.save(model);
    }

    @Override
    public List<UsagePatternModel> getAll() {
        return modelRepository.findAll();
    }

    @Override
    public List<UsagePatternModel> getByBinId(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        return modelRepository.findByBin(bin);
    }
}