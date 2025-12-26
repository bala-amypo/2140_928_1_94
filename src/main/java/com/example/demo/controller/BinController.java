package com.example.demo.controller;

import com.example.demo.model.Bin;
import com.example.demo.service.BinService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/bins")
@Tag(name = "Bin Management", description = "APIs for managing waste bins")
public class BinController {

    @Autowired
    private BinService binService;

    @PostMapping
    @Operation(summary = "Create a new bin", description = "Creates a new waste bin with the provided details")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Bin created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input"),
        @ApiResponse(responseCode = "404", description = "Zone not found")
    })
    public ResponseEntity<Bin> createBin(@RequestBody Bin bin) {
        Bin createdBin = binService.createBin(bin);
        return new ResponseEntity<>(createdBin, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get bin by ID", description = "Returns a bin based on ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the bin"),
        @ApiResponse(responseCode = "404", description = "Bin not found")
    })
    public ResponseEntity<Bin> getBinById(
            @Parameter(description = "ID of bin to be retrieved", required = true)
            @PathVariable Long id) {
        Bin bin = binService.getBinById(id);
        return ResponseEntity.ok(bin);
    }

    @GetMapping
    @Operation(summary = "Get all bins", description = "Returns list of all bins")
    public ResponseEntity<List<Bin>> getAllBins() {
        List<Bin> bins = binService.getAllBins();
        return ResponseEntity.ok(bins);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update bin", description = "Updates an existing bin")
    public ResponseEntity<Bin> updateBin(@PathVariable Long id, @RequestBody Bin bin) {
        Bin updatedBin = binService.updateBin(id, bin);
        return ResponseEntity.ok(updatedBin);
    }

    @PutMapping("/{id}/deactivate")
    @Operation(summary = "Deactivate bin", description = "Deactivates a bin (soft delete)")
    public ResponseEntity<Map<String, String>> deactivateBin(@PathVariable Long id) {
        binService.deactivateBin(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Bin deactivated successfully");
        response.put("binId", id.toString());
        return ResponseEntity.ok(response);
    }
}