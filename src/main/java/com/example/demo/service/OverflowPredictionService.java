package com.example.demo.service;

import com.example.demo.model.OverflowPrediction;

import java.util.List;

public interface OverflowPredictionService {

    OverflowPrediction create(OverflowPrediction prediction);

    OverflowPrediction getById(Long id);

    List<OverflowPrediction> getAll();
}
