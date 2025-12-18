package com.example.demo.repository;

import com.example.demo.model.Zone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ZoneRepository extends JpaRepository<Zone, Long> {
    Zone findByZoneName(String name);
}
