package com.example.demo.service.impl;

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

    private final FillLevelRecordRepository repository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository repository, BinRepository binRepository) {
        this.repository = repository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        if (record.getRecordedAt().isAfter(LocalDateTime.now())) throw new RuntimeException("recordedAt cannot be future");
        return repository.save(record);
    }

    @Override
    public FillLevelRecord getRecordById(long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("FillLevelRecord not found"));
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(long binId) {
        Bin bin = binRepository.findById(binId).orElseThrow(() -> new RuntimeException("Bin not found"));
        return repository.findByBinOrderByRecordedAtDesc(bin);
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(long binId, int limit) {
        List<FillLevelRecord> all = getRecordsForBin(binId);
        return all.subList(0, Math.min(limit, all.size()));
    }
}
