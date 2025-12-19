package com.example.demo.service;

import com.example.demo.model.Zone;
import java.util.List;

public interface ZoneService {
    Zone save(Zone zone);
    List<Zone> findAll();
    void delete(Long id);
}
