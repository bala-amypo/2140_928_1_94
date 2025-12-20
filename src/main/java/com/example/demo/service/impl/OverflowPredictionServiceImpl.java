package com.example.demo.service.impl;

import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    @Override
    public List<Object> getAll() {
        return List.of();
    }
}
