package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;

import java.util.List;

public interface UsagePatternModelService {

    UsagePatternModel createModel(UsagePatternModel model);

    UsagePatternModel updateModel(long id, UsagePatternModel model);

    UsagePatternModel getModelForBin(long binId);

    List<UsagePatternModel> getAllModels();
}
