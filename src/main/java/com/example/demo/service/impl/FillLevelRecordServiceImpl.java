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

    public FillLevelRecord create(FillLevelRecord record) {
        return repository.save(record);
    }

    public List<FillLevelRecord> getAll() {
        return repository.findAll();
    }

    public FillLevelRecord getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public FillLevelRecord update(Long id, FillLevelRecord record) {
        record.setId(id);
        return repository.save(record);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
