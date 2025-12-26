package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}