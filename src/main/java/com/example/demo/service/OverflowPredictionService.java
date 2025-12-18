package com.example.demo.service;

import java.util.List;
import com.example.demo.model.OverflowPrediction;

public interface OverflowPredictionService {
    OverflowPrediction create(OverflowPrediction data);
    List<OverflowPrediction> getAll();
    OverflowPrediction getById(Long id);
    OverflowPrediction update(Long id, OverflowPrediction data);
    void delete(Long id);
}
