package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    // All predictions for a given bin
    List<OverflowPrediction> findByBin(Bin bin);

    // All predictions for a zone
    List<OverflowPrediction> findByBin_Zone_Id(Long zoneId);

    // Latest prediction for a zone
    Optional<OverflowPrediction> findTopByBin_Zone_IdOrderByPredictedAtDesc(Long zoneId);
}
