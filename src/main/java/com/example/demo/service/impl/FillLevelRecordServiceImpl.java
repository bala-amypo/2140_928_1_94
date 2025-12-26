package com.example.demo.service.impl;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
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
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        if (!bin.getActive()) {
            throw new BadRequestException("Cannot add record to inactive bin");
        }
        
        if (record.getFillPercentage() < 0 || record.getFillPercentage() > 100) {
            throw new BadRequestException("Fill percentage must be between 0 and 100");
        }
        
        if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Record date cannot be in the future");
        }
        
        record.setBin(bin);
        return recordRepository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return recordRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
            .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        
        List<FillLevelRecord> allRecords = recordRepository.findByBinOrderByRecordedAtDesc(bin);
        return allRecords.stream().limit(limit).toList();
    }
}