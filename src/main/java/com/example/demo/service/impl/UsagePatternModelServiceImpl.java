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

    private final UsagePatternModelRepository repository;
    private final BinRepository binRepository;

    public UsagePatternModelServiceImpl(
            UsagePatternModelRepository repository,
            BinRepository binRepository) {
        this.repository = repository;
        this.binRepository = binRepository;
    }

    @Override
    public UsagePatternModel create(UsagePatternModel model) {
        Long binId = model.getBin().getId();

        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        model.setBin(bin);
        return repository.save(model);
    }

    @Override
    public UsagePatternModel update(Long id, UsagePatternModel model) {
        UsagePatternModel existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usage Pattern Model not found"));

        Long binId = model.getBin().getId();
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        existing.setBin(bin);
        existing.setAverageDailyUsage(model.getAverageDailyUsage());
        existing.setPeakUsage(model.getPeakUsage());
        existing.setPatternType(model.getPatternType());

        return repository.save(existing);
    }

    @Override
    public List<UsagePatternModel> getAll() {
        return repository.findAll();
    }

    @Override
    public List<UsagePatternModel> getByBinId(Long binId) {
        return repository.findByBinId(binId);
    }
}
