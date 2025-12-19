package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    private final FillLevelRecordService service;

    public FillLevelRecordController(FillLevelRecordService service) {
        this.service = service;
    }

    @PostMapping
    public FillLevelRecord create(@RequestBody FillLevelRecord record) {
        return service.create(record);
    }

    @GetMapping("/{id}")
    public FillLevelRecord getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/bin/{binId}")
    public List<FillLevelRecord> getByBin(@PathVariable Long binId) {
        return service.getByBin(binId);
    }

    @GetMapping("/bin/{binId}/recent")
    public List<FillLevelRecord> getRecentByBin(
            @PathVariable Long binId,
            @RequestParam int limit
    ) {
        return service.getRecentByBin(binId, limit);
    }
}
