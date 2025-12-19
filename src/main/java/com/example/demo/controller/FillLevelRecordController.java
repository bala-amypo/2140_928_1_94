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

    @PostMapping("/")
    public ResponseEntity<FillLevelRecord> createRecord(@RequestBody FillLevelRecord record) {
        return ResponseEntity.ok(service.createRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FillLevelRecord> getRecord(@PathVariable long id) {
        return ResponseEntity.ok(service.getRecordById(id));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<FillLevelRecord>> getRecordsForBin(@PathVariable long binId) {
        return ResponseEntity.ok(service.getRecordsForBin(binId));
    }

    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<List<FillLevelRecord>> getRecentRecords(
            @PathVariable long binId, @RequestParam int limit) {
        return ResponseEntity.ok(service.getRecentRecords(binId, limit));
    }
}
