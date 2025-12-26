package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class package com.example.demo.service.impl;

import com.example.demo.exception.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;

import java.time.LocalDateTime;
import java.util.List;

public class FillLevelRecordServiceImpl {

    private final FillLevelRecordRepository repo;
    private final BinRepository binRepo;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository r, BinRepository b) {
        repo = r;
        binRepo = b;
    }

    public FillLevelRecord createRecord(FillLevelRecord r) {
        Bin bin = binRepo.findById(r.getBin().getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        if (!bin.getActive()) throw new BadRequestException("Inactive bin");
        if (r.getRecordedAt().isAfter(LocalDateTime.now()))
            throw new BadRequestException("Future date");

        return repo.save(r);
    }

    public FillLevelRecord getRecordById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepo.findById(binId).orElseThrow();
        List<FillLevelRecord> list = repo.findByBinOrderByRecordedAtDesc(bin);
        return list.subList(0, Math.min(limit, list.size()));
    }
}
 {

    private final FillLevelRecordRepository repo;
    private final BinRepository binRepo;

    public FillLevelRecordServiceImpl(FillLevelRecordRepository r, BinRepository b) {
        repo = r;
        binRepo = b;
    }

    public FillLevelRecord createRecord(FillLevelRecord r) {
        Bin bin = binRepo.findById(r.getBin().getId())
                .orElseThrow(() -> new BadRequestException("Bin not found"));

        if (!bin.getActive()) throw new BadRequestException("Inactive bin");
        if (r.getRecordedAt().isAfter(LocalDateTime.now()))
            throw new BadRequestException("Future date");

        return repo.save(r);
    }

    public FillLevelRecord getRecordById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found"));
    }

    public List<FillLevelRecord> getRecentRecords(Long binId, int limit) {
        Bin bin = binRepo.findById(binId).orElseThrow();
        List<FillLevelRecord> list = repo.findByBinOrderByRecordedAtDesc(bin);
        return list.subList(0, Math.min(limit, list.size()));
    }
}
