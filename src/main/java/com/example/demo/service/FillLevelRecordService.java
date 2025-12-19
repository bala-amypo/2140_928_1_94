package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;
import java.util.List;

public interface FillLevelRecordService {

    FillLevelRecord save(FillLevelRecord record);

    List<FillLevelRecord> findAll();

    FillLevelRecord findById(Long id);

    void delete(Long id);
}
