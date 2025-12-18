package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {
    Bin create(Bin bin);
    List<Bin> getAll();
    Bin getById(Long id);
    Bin update(Long id, Bin bin);
    void delete(Long id);
}
