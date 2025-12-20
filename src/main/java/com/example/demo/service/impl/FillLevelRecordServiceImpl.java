package com.example.demo.service.impl;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    @Override
    public FillLevelRecord createRecord(FillLevelRecord record) {
        return record;
    }

    @Override
    public FillLevelRecord getRecordById(Long id) {
        return null;
    }

    @Override
    public List<FillLevelRecord> getRecordsForBin(Long binId) {
        return List.of();
    }

    @Override
    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        return List.of();
    }
}
