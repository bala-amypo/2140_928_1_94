package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.repository.BinRepository;
import com.example.demo.service.BinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BinServiceImpl implements BinService {

    @Autowired
    private BinRepository binRepository;

    @Override
    public Bin create(Bin bin) {
        return binRepository.save(bin);
    }

    @Override
    public Bin update(Long id, Bin binDetails) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + id));

        existingBin.setIdentifier(binDetails.getIdentifier());
        existingBin.setLocationDescription(binDetails.getLocationDescription());
        existingBin.setLatitude(binDetails.getLatitude());
        existingBin.setLongitude(binDetails.getLongitude());
        existingBin.setZone(binDetails.getZone());
        existingBin.setCapacityLiters(binDetails.getCapacityLiters());
        existingBin.setActive(binDetails.getActive());

        return binRepository.save(existingBin);
    }

    @Override
    public Bin getById(Long id) {
        return binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + id));
    }

    @Override
    public List<Bin> getAll() {
        return binRepository.findAll();
    }

    @Override
    public void deactivate(Long id) {
        Bin existingBin = binRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + id));
        existingBin.setActive(false);
        binRepository.save(existingBin);
    }
}
