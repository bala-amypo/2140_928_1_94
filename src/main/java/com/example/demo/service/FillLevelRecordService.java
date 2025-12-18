package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;
import java.util.List;

public interface FillLevelRecordService {
    FillLevelRecord createRecord(FillLevelRecord record);
    List<FillLevelRecord> getRecordsForBin(long binId);
    FillLevelRecord getRecordById(long id);
    List<FillLevelRecord> getRecentRecords(long binId, int limit);
}
