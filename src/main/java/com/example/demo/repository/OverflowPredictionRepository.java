package com.example.demo.repository;

import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OverflowPredictionRepository extends JpaRepository<OverflowPrediction, Long> {
    
    @Query("SELECT p FROM OverflowPrediction p " +
           "WHERE p.bin.zone = :zone " +
           "ORDER BY p.generatedAt DESC")
    List<OverflowPrediction> findLatestPredictionsForZone(@Param("zone") Zone zone);
    
    @Query("SELECT p FROM OverflowPrediction p " +
           "WHERE p.bin.id = :binId " +
           "ORDER BY p.generatedAt DESC")
    List<OverflowPrediction> findLatestByBinId(@Param("binId") Long binId);
}