package com.example.demo.service;

import com.example.demo.model.Bin;

import java.util.List;
import java.util.Optional;

public interface BinService {
    Bin createBin(Bin bin);
    Optional<Bin> getBinById(Long id);
    List<Bin> getAllBins();
    Bin updateBin(Long id, Bin bin);
    Bin deactivateBin(Long id);
}
