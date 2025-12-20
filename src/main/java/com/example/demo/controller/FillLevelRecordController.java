package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<FillLevelRecord> create(@RequestBody FillLevelRecord record) {
        return ResponseEntity.ok(service.create(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FillLevelRecord> get(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<FillLevelRecord>> byBin(@PathVariable Long binId) {
        return ResponseEntity.ok(service.getByBin(binId));
    }

    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<List<FillLevelRecord>> recent(
            @PathVariable Long binId,
            @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(service.getRecent(binId, limit));
    }
}
