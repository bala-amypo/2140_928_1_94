package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findTop5ByBin_Zone_IdOrderByPredictedAtDesc(Long zoneId);

    List<OverflowPrediction> findByBin_Id(Long binId);
}
