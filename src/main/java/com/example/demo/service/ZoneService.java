package com.example.demo.service;

import com.example.demo.model.Zone;

import java.util.List;

public interface ZoneService {

    Zone create(Zone zone);

    Zone getById(Long id);

    List<Zone> getAll();

    Zone update(Long id, Zone zone);

    void deactivate(Long id);
}
