package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FillLevelRecordServiceImpl implements FillLevelRecordService {
    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository, 
                                     BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        Bin bin = binRepository.findById(record.getBin().getId())
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + record.getBin().getId()));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot add record to inactive bin");
        }
        
        if (record.getFillPercentage() == null || record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("Fill percentage must be between 0 and 100");
        }
        
        if (record.getRecordedAt() != null && record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Record date cannot be in the future");
        }
        
        // Set recordedAt to now if not provided
        if (record.getRecordedAt() == null) {
            record.setRecordedAt(LocalDateTime.now());
        }
        
        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    @Transactional(readOnly = true)
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found with id: " + binId));
        
        List<FillLevelRecord> allRecords = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        return allRecords.stream().limit(limit).toList();
    }
    
    // Additional method to get current fill percentage
    @Transactional(readOnly = true)
    public Double getCurrentFillPercentage(Long binId) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return recordRepository.findTop1ByBinOrderByRecordedAtDesc(bin)
            .map(FillLevelRecord::getFillPercentage)
            .orElse(0.0);
    }
    
    // Additional method to get records within time range
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecordsByDateRange(Long binId, LocalDateTime start, LocalDateTime end) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        return recordRepository.findByBinAndRecordedAtBetween(bin, start, end);
    }
}