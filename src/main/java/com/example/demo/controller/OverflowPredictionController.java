package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fill-level-records")
public class FillLevelRecordController {

    private final FillLevelRecordService service;

    public FillLevelRecordController(FillLevelRecordService service) {
        this.service = service;
    }

    @PostMapping
    public FillLevelRecord save(@RequestBody FillLevelRecord record) {
        return service.save(record);
    }

    @GetMapping
    public List<FillLevelRecord> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public FillLevelRecord findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
