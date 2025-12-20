package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.List;
import java.util.Optional;

public interface UsagePatternModelService {
    UsagePatternModel createModel(UsagePatternModel model);
    UsagePatternModel updateModel(Long id, UsagePatternModel model);
    Optional<UsagePatternModel> getModelForBin(Long binId);
    List<UsagePatternModel> getAllModels();
}
