package com.example.demo.service.impl;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional  // All create/update operations are transactional
public class FillLevelRecordServiceImpl implements FillLevelRecordService {

    private final FillLevelRecordRepository recordRepository;
    private final BinRepository binRepository;

    public FillLevelRecordServiceImpl(
            FillLevelRecordRepository recordRepository,
            BinRepository binRepository
    ) {
        this.recordRepository = recordRepository;
        this.binRepository = binRepository;
    }

    @Override
    public FillLevelRecord create(FillLevelRecord record) {
        return recordRepository.save(record);
    }

    @Override
    @Transactional(readOnly = true)
    public FillLevelRecord getById(Long id) {
        return recordRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("FillLevelRecord not found with id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getByBin(Long binId) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + binId));
        return recordRepository.findByBin(bin);
    }

    @Override
    @Transactional(readOnly = true)
    public List<FillLevelRecord> getRecent(Long binId, int limit) {
        Bin bin = binRepository.findById(binId)
                .orElseThrow(() -> new RuntimeException("Bin not found with id " + binId));
        return recordRepository.findAll();
    }
}
