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

    private final FillLevelRecordRepository fillLevelRecordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository fillLevelRecordRepository,
                                      BinRepository binRepository) {
        this.fillLevelRecordRepository = fillLevelRecordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record.getRecordedAt().isAfter(LocalDateTime.now())) {
            throw new BadRequestException("recordedAt cannot be in the future");
        }
        Bin bin = binRepository.findById(record.getBin().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        record.setBin(bin);
        return fillLevelRecordRepository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(long id) {
        return fillLevelRecordRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("FillLevelRecord not found"));
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new ResourceNotFoundException("Bin not found"));
        return fillLevelRecordRepository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(long binId, int limit) {
        List<FillLevelRecord> records = getRecordsForBin(binId);
        return records.stream().limit(limit).toList();
    }
}
