package com.example.demo.service;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.UsagePatternModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelService {

    private final UsagePatternModelRepository modelRepository;
    private final BinRepository binRepository;

    public UsagePatternModelService(UsagePatternModelRepository modelRepository,
                                    BinRepository binRepository) {
        this.modelRepository = modelRepository;
        this.binRepository = binRepository;
    }

    // ===== CREATE =====
    public UsagePatternModel create(UsagePatternModel model) {

        if (model.getBin() == null || model.getBin().getId() == null) {
            throw new RuntimeException("Bin id is required");
        }

        Bin bin = binRepository.findById(model.getBin().getId())
                .orElseThrow(() -> new RuntimeException("Bin not found"));

        model.setBin(bin);

        return modelRepository.save(model);
    }

    // ===== UPDATE =====
    public UsagePatternModel update(Long id, UsagePatternModel model) {

        UsagePatternModel existing = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usage pattern model not found"));

        existing.setAverageDailyUsage(model.getAverageDailyUsage());
        existing.setPeakUsage(model.getPeakUsage());
        existing.setPatternType(model.getPatternType());

        if (model.getBin() != null && model.getBin().getId() != null) {
            Bin bin = binRepository.findById(model.getBin().getId())
                    .orElseThrow(() -> new RuntimeException("Bin not found"));
            existing.setBin(bin);
        }

        return modelRepository.save(existing);
    }

    // ===== GET ALL =====
    public List<UsagePatternModel> getAll() {
        return modelRepository.findAll();
    }

    // ===== GET BY BIN =====
    public List<UsagePatternModel> getByBinId(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found"));
        return modelRepository.findByBin(bin);
    }
}
