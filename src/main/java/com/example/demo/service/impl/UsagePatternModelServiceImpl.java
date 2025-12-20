package com.example.demo.service.impl;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.service.UsagePatternModelService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsagePatternModelServiceImpl implements UsagePatternModelService {

    @Override
    public UsagePatternModel createModel(UsagePatternModel model) {
        return model;
    }

    @Override
    public UsagePatternModel updateModel(Long id, UsagePatternModel model) {
        return model;
    }

    @Override
    public UsagePatternModel getModelForBin(Long binId) {
        return null;
    }

    @Override
    public List<UsagePatternModel> getAllModels() {
        return List.of();
    }
}
