package com.example.demo.repository;

import com.example.demo.model.FillLevelRecord;

import java.util.List;

public interface FillLevelRecordRepository {

    FillLevelRecord save(FillLevelRecord record);

    List<FillLevelRecord> findByBinId(Long binId);
}
