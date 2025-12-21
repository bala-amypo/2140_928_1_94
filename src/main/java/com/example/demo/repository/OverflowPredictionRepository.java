package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBin(Bin bin);

    Optional<OverflowPrediction> findTopByBin_Zone_IdOrderByPredictedAtDesc(Long zoneId);
}
