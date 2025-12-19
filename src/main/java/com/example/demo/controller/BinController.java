package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {
    Bin create(Bin bin);
    Bin update(Long id, Bin bin);
    Bin getById(Long id);
    List<Bin> getAll();
    void deactivate(Long id);
}
