package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;
import java.util.List;

public interface FillLevelRecordService {
    FillLevelRecord createRecord(FillLevelRecord record);
    FillLevelRecord getRecordById(long id);
    List<FillLevelRecord> getRecordsForBin(long binId);
    List<FillLevelRecord> getRecentRecords(long binId, int limit);
}
