package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.repository.UsagePatternModelRepository;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    private final UsagePatternModelRepository repository;

    public UsagePatternModelServiceImpl(UsagePatternModelRepository repository) {
        this.repository = repository;
    }

    @Override
    public UsagePatternModel create(UsagePatternModel model) {
        return repository.save(model);
    }

    @Override
    public UsagePatternModel update(Long id, UsagePatternModel model) {
        Optional<UsagePatternModel> existing = repository.findById(id);
        if (existing.isPresent()) {
            UsagePatternModel m = existing.get();
            m.setBinId(model.getBinId());
            m.setPatternData(model.getPatternData());
            return repository.save(m);
        }
        return null;
    }

    @Override
    public UsagePatternModel getByBin(Long binId) {
        return repository.findByBinId(binId);
    }

    @Override
    public List<UsagePatternModel> getAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
