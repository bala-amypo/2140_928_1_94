package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;

import java.util.List;
import java.util.Optional;

public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return recordRepository.save(record);
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        return recordRepository.findByBinId(binId);
    }

    @Override
    public Optional<FillLevelRecord> getLatestRecordForBin(Long binId) {
        return recordRepository.findTop1ByBinIdOrderByRecordedAtDesc(binId);
    }
}
