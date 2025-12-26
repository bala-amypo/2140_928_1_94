package com.example.demo.controller;

import com.example.demo.model.FillLevelRecord;
import com.example.demo.service.FillLevelRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fill-records")
@Tag(name = "Fill Level Records", description = "APIs for managing bin fill level records")
public class FillLevelRecordController {

    @Autowired
    private FillLevelRecordService fillLevelRecordService;

    @PostMapping
    @Operation(summary = "Create fill record", description = "Create a new fill level record for a bin")
    public ResponseEntity<FillLevelRecord> createRecord(@RequestBody FillLevelRecord record) {
        FillLevelRecord created = fillLevelRecordService.createRecord(record);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get fill record by ID", description = "Get a specific fill record")
    public ResponseEntity<FillLevelRecord> getRecordById(@PathVariable Long id) {
        FillLevelRecord record = fillLevelRecordService.getRecordById(id);
        return ResponseEntity.ok(record);
    }

    @GetMapping("/bin/{binId}")
    @Operation(summary = "Get records for bin", description = "Get all fill records for a specific bin")
    public ResponseEntity<List<FillLevelRecord>> getRecordsForBin(@PathVariable Long binId) {
        List<FillLevelRecord> records = fillLevelRecordService.getRecordsForBin(binId);
        return ResponseEntity.ok(records);
    }

    @GetMapping("/bin/{binId}/recent")
    @Operation(summary = "Get recent records", description = "Get recent fill records with limit")
    public ResponseEntity<List<FillLevelRecord>> getRecentRecords(
            @PathVariable Long binId,
            @RequestParam(defaultValue = "10") int limit) {
        List<FillLevelRecord> records = fillLevelRecordService.getRecentRecords(binId, limit);
        return ResponseEntity.ok(records);
    }
}