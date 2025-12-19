package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository repository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                         FillLevelRecordRepository recordRepository,
                                         UsagePatternModelRepository modelRepository,
                                         OverflowPredictionRepository repository,
                                         ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.repository = repository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        // Simplified: real logic should compute predictedOverflowTime
        return repository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("OverflowPrediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        return repository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new RuntimeException("Zone not found"));
        return repository.findLatestPredictionsForZone(zone);
    }
}
package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.model.Zone;
import com.example.demo.repository.*;
import com.example.demo.service.OverflowPredictionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OverflowPredictionServiceImpl implements OverflowPredictionService {

    private final BinRepository binRepository;
    private final FillLevelRecordRepository recordRepository;
    private final UsagePatternModelRepository modelRepository;
    private final OverflowPredictionRepository repository;
    private final ZoneRepository zoneRepository;

    public OverflowPredictionServiceImpl(BinRepository binRepository,
                                         FillLevelRecordRepository recordRepository,
                                         UsagePatternModelRepository modelRepository,
                                         OverflowPredictionRepository repository,
                                         ZoneRepository zoneRepository) {
        this.binRepository = binRepository;
        this.recordRepository = recordRepository;
        this.modelRepository = modelRepository;
        this.repository = repository;
        this.zoneRepository = zoneRepository;
    }

    @Override
    public OverflowPrediction generatePrediction(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);
        // Simplified: real logic should compute predictedOverflowTime
        return repository.save(prediction);
    }

    @Override
    public OverflowPrediction getPredictionById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("OverflowPrediction not found"));
    }

    @Override
    public List<OverflowPrediction> getPredictionsForBin(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        return repository.findByBin(bin);
    }

    @Override
    public List<OverflowPrediction> getLatestPredictionsForZone(long zoneId) {
        Zone zone = zoneRepository.findById(zoneId).orElseThrow(() -> new RuntimeException("Zone not found"));
        return repository.findLatestPredictionsForZone(zone);
    }
}
