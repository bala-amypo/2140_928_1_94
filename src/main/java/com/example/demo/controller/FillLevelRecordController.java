package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-level-records")
public class FillLevelRecordController {

    private final FillLevelRecordService fillLevelRecordService;

    public FillLevelRecordController(FillLevelRecordService fillLevelRecordService) {
        this.fillLevelRecordService = fillLevelRecordService;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return fillLevelRecordService.create(record);
    }

    @GetMapping
    public List<FillLevelRecord> getAll() {
        return fillLevelRecordService.getAll();
    }

    @GetMapping("/{id}")
    public FillLevelRecord getById(@PathVariable Long id) {
        return fillLevelRecordService.getById(id);
    }

    @PutMapping("/{id}")
    public FillLevelRecord update(
            @PathVariable Long id,
            @RequestBody FillLevelRecord record
    ) {
        return fillLevelRecordService.update(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        fillLevelRecordService.delete(id);
    }
}
