package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;
import java.util.List;

public interface FillLevelRecordService {
    FillLevelRecord create(FillLevelRecord record);
    FillLevelRecord getById(Long id);
    List<FillLevelRecord> getByBin(Long binId);
    List<FillLevelRecord> getRecentByBin(Long binId, int limit);
    void delete(Long id);
}
