package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.Zone;

public interface ZoneRepository {
    Zone save(Zone z);
    Optional<Zone> findById(Long id);
    Optional<Zone> findByZoneName(String name);
}
