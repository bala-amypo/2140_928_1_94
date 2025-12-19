package com.example.demo.service;

import com.example.demo.model.Bin;
import java.util.List;

public interface BinService {

    Bin createBin(Bin bin);

    Bin updateBin(long id, Bin bin);

    Bin getBinById(long id);

    List<Bin> getAllBins();

    void deactivateBin(long id);
}
