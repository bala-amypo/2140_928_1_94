package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;

import java.util.List;

public class FillLevelRecordServiceImpl {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository,
                                      BinRepository binRepository) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Fill level record cannot be null");
        }

        if (record.getBin() == null || record.getBin().getId() == null) {
            throw new IllegalArgumentException("Bin information is required");
        }

        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        record.setBin(bin);
        return recordRepository.save(record);
    }

    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        if (binId == null) {
            throw new IllegalArgumentException("Bin ID cannot be null");
        }

        // Validate bin existence
        binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));

        return recordRepository.findByBinId(binId);
    }
}
