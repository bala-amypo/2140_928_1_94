package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface BinRepository {
    Bin save(Bin b);
    Optional<Bin> findById(Long id);
    List<Bin> findAll();
    Optional<Bin> findByIdentifier(String identifier);
    List<Bin> findByZoneAndActiveTrue(Zone zone);
}
