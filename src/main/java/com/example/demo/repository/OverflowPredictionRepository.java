package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {

    List<OverflowPrediction> findByBin(Bin bin);

    @Query("SELECT o FROM OverflowPrediction o WHERE o.bin.zone = :zone ORDER BY o.createdAt DESC")
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
