package com.example.demo.repository;

import com.example.demo.entity.Bin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BinRepository extends JpaRepository<Bin, Long> {
    boolean existsByIdentifier(String identifier);
}
