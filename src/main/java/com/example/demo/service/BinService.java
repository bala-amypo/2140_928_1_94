package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {

    Bin save(Bin bin);

    List<Bin> findAll();

    Bin findById(Long id);

    void delete(Long id);
}
