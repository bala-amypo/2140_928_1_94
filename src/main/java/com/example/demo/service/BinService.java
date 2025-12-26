package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {
    Bin createBin(Bin bin);
    Bin getBinById(Long id);
    Bin updateBin(Long id, Bin bin);
    void deactivateBin(Long id);
    List<Bin> getAllBins();
}

// Similar interfaces for other services