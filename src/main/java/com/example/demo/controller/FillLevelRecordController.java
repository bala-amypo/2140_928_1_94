package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-levels")
public class FillLevelRecordController {

    private final FillLevelRecordService service;

    public FillLevelRecordController(FillLevelRecordService service) {
        this.service = service;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return service.createRecord(record);
    }

    @GetMapping
    public List<FillLevelRecord> getAll() {
        return service.getAllRecords();
    }

    @GetMapping("/{id}")
    public FillLevelRecord getById(@PathVariable Long id) {
        return service.getRecordById(id);
    }

    @PutMapping("/{id}")
    public FillLevelRecord update(@PathVariable Long id, @RequestBody FillLevelRecord record) {
        return service.updateRecord(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteRecord(id);
    }
}
