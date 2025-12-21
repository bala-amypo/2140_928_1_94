package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.List;

public interface UsagePatternModelService {

    UsagePatternModel createModel(UsagePatternModel model);

    UsagePatternModel updateModel(Long id, UsagePatternModel updatedModel);

    List<UsagePatternModel> getAllModels();

    UsagePatternModel getModelForBin(Long binId);
}