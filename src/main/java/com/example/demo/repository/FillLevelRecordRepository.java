package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;

import java.util.List;
import java.util.Optional;

public interface FillLevelRecordRepository {

    FillLevelRecord save(FillLevelRecord record);

    List<FillLevelRecord> findByBinId(Long binId);

    // Explicit, plain-Java method
    Optional<FillLevelRecord> findLatestByBin(Bin bin);
}
