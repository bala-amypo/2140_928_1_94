package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;
import java.util.List;

public interface UsagePatternModelService {
    UsagePatternModel create(UsagePatternModel model);
    UsagePatternModel update(Long id, UsagePatternModel model);
    UsagePatternModel getByBin(Long binId);
    List<UsagePatternModel> getAll();
    void delete(Long id);
}
