package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BinRepository extends JpaRepository<Bin, Long> {
    Optional<Bin> findByIdentifier(String identifier);
    
    List<Bin> findByZoneAndActiveTrue(Zone zone);
    
    List<Bin> findByActiveTrue();
    
    @Query("SELECT b FROM Bin b WHERE b.zone.id = :zoneId AND b.active = true")
    List<Bin> findActiveBinsByZoneId(@Param("zoneId") Long zoneId);
    
    @Query("SELECT COUNT(b) FROM Bin b WHERE b.zone.id = :zoneId AND b.active = true")
    Long countActiveBinsByZone(@Param("zoneId") Long zoneId);
}