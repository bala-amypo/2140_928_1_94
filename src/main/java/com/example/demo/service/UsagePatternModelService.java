package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;
import java.util.List;

public interface UsagePatternModelService {

    UsagePatternModel create(UsagePatternModel model);

    UsagePatternModel update(Long id, UsagePatternModel model);

    List<UsagePatternModel> getAll();

    List<UsagePatternModel> getByBinId(Long binId);
}