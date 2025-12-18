package com.example.demo.service;

import com.example.demo.model.FillLevelRecord;
import java.util.List;

public interface FillLevelRecordService {

    FillLevelRecord createRecord(FillLevelRecord record);

    FillLevelRecord getRecordById(Long id);

    List<FillLevelRecord> getAllRecords();

    FillLevelRecord updateRecord(Long id, FillLevelRecord record);

    void deleteRecord(Long id);
}
