package com.example.demo.service;

import com.example.demo.model.UsagePatternModel;
import java.util.List;

public interface UsagePatternModelService {

    UsagePatternModel save(UsagePatternModel model);

    List<UsagePatternModel> findAll();

    UsagePatternModel findById(Long id);

    void delete(Long id);
}
