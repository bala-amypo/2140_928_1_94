package com.example.demo.service;

import com.example.demo.model.Bin;

import java.util.List;

public interface BinService {

    Bin create(Bin bin);

    Bin getById(Long id);

    List<Bin> getAll();

    Bin update(Long id, Bin bin);

    void deactivate(Long id);
}
