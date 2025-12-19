package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
}
