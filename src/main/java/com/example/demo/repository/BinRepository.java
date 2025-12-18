package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BinRepository extends JpaRepository<Bin, Long> {
    Bin findByIdentifier(String identifier);
    List<Bin> findByZoneAndActiveTrue(Zone zone);
}
