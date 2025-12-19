package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fill-levels")
public class FillLevelRecordController {

    private final FillLevelRecordService service;

    public FillLevelRecordController(FillLevelRecordService service) {
        this.service = service;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return service.create(record);
    }

    @GetMapping
    public List<FillLevelRecord> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public FillLevelRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public FillLevelRecord update(@PathVariable Long id, @RequestBody FillLevelRecord record) {
        return service.update(id, record);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
