package com.example.demo.controller;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
public class FillLevelRecordController {

    @Autowired
    private FillLevelRecordService recordService;

    @PostMapping
    public ResponseEntity<FillLevelRecord> createRecord(@RequestBody FillLevelRecord record) {
        return ResponseEntity.ok(recordService.createRecord(record));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FillLevelRecord> getRecord(@PathVariable Long id) {
        return ResponseEntity.ok(recordService.getRecordById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Record not found with id " + id)));
    }

    @GetMapping("/bin/{binId}")
    public ResponseEntity<List<FillLevelRecord>> getRecordsForBin(@PathVariable Long binId) {
        return ResponseEntity.ok(recordService.getRecordsForBin(binId));
    }

    @GetMapping("/bin/{binId}/recent")
    public ResponseEntity<List<FillLevelRecord>> getRecentRecords(@PathVariable Long binId,
                                                                  @RequestParam(defaultValue = "5") int limit) {
        return ResponseEntity.ok(recordService.getRecentRecords(binId, limit));
    }
}
