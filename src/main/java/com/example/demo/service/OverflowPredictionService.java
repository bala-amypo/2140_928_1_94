package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;
import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction save(OverflowPrediction prediction);

    List<OverflowPrediction> findAll();

    OverflowPrediction findById(Long id);

    void delete(Long id);
}
