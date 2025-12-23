package com.example.demo.repository;

import com.example.demo.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BinRepository extends JpaRepository<Bin, Long> {

    Optional<Bin> findByIdentifier(String identifier);

    @Query("SELECT b FROM Bin b JOIN FETCH b.zone")
    List<Bin> findAllWithZone();

    @Query("SELECT b FROM Bin b JOIN FETCH b.zone WHERE b.id = :id")
    Optional<Bin> findByIdWithZone(Long id);
}
