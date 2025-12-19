package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository repository;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository repository) {
        this.repository = repository;
    }

    @Override
    public FillLevelRecord create(FillLevelRecord record) {
        return repository.save(record);
    }

    @Override
    public FillLevelRecord getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<FillLevelRecord> getByBin(Long binId) {
        return repository.findByBinId(binId);
    }

    @Override
    public List<FillLevelRecord> getRecentByBin(Long binId, int limit) {
        return repository.findTopNByBinIdOrderByTimestampDesc(binId, limit);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
