package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;

import java.util.List;
import java.util.Optional;

public interface FillLevelRecordService {
    FillLevelRecord createRecord(FillLevelRecord record);
    Optional<FillLevelRecord> getRecordById(Long id);
    List<FillLevelRecord> getRecordsForBin(Long binId);
    List<FillLevelRecord> getRecentRecords(Long binId, int limit);
}
