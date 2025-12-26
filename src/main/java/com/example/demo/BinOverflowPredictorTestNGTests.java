package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.impl.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class BinOverflowPredictorTestNGTests {

    @InjectMocks
    private BinServiceImpl binService;

    @InjectMocks
    private ZoneServiceImpl zoneService;

    @InjectMocks
    private OverflowPredictionServiceImpl overflowService;

    @InjectMocks
    private UsagePatternModelServiceImpl modelService;

    @InjectMocks
    private FillLevelRecordServiceImpl recordService;

    @Mock
    private BinRepository binRepository;

    @Mock
    private ZoneRepository zoneRepository;

    @Mock
    private OverflowPredictionRepository overflowPredictionRepository;

    @Mock
    private UsagePatternModelRepository modelRepository;

    @Mock
    private FillLevelRecordRepository recordRepository;

    @BeforeMethod
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ---------------- BinService Tests ----------------
    @Test
    public void testCreateBin() {
        Bin bin = new Bin();
        bin.setIdentifier("BIN123");
        bin.setCapacityLiters(100.0);

        when(binRepository.save(bin)).thenReturn(bin);

        Bin created = binService.create(bin);
        assertNotNull(created);
        assertEquals(created.getIdentifier(), "BIN123");
    }

    @Test
    public void testUpdateBin() {
        Bin existing = new Bin();
        existing.setId(1L);
        existing.setIdentifier("OLD");

        Bin updated = new Bin();
        updated.setIdentifier("NEW");

        when(binRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(binRepository.save(existing)).thenReturn(existing);

        Bin result = binService.update(1L, updated);
        assertEquals(result.getIdentifier(), "NEW");
    }

    @Test
    public void testDeactivateBin() {
        Bin existing = new Bin();
        existing.setId(1L);
        existing.setActive(true);

        when(binRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(binRepository.save(existing)).thenReturn(existing);

        binService.deactivate(1L);
        assertFalse(existing.getActive());
    }

    // ---------------- ZoneService Tests ----------------
    @Test
    public void testCreateZone() {
        Zone zone = new Zone();
        zone.setZoneName("ZoneA");
        zone.setActive(true);

        when(zoneRepository.save(zone)).thenReturn(zone);

        Zone created = zoneService.create(zone);
        assertNotNull(created);
        assertEquals(created.getZoneName(), "ZoneA");
    }

    @Test
    public void testUpdateZone() {
        Zone existing = new Zone();
        existing.setId(1L);
        existing.setZoneName("Old");

        Zone updated = new Zone();
        updated.setZoneName("New");

        when(zoneRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(zoneRepository.save(existing)).thenReturn(existing);

        Zone result = zoneService.update(1L, updated);
        assertEquals(result.getZoneName(), "New");
    }

    @Test
    public void testDeactivateZone() {
        Zone zone = new Zone();
        zone.setId(1L);
        zone.setActive(true);

        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(zoneRepository.save(zone)).thenReturn(zone);

        zoneService.deactivate(1L);
        assertFalse(zone.getActive());
    }

    // ---------------- OverflowPredictionService Tests ----------------
    @Test
    public void testGenerateOverflowPrediction() {
        Bin bin = new Bin();
        bin.setId(1L);

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(overflowPredictionRepository.save(any())).thenReturn(prediction);

        OverflowPrediction result = overflowService.generatePrediction(1L);
        assertNotNull(result);
        assertEquals(result.getBin(), bin);
    }

    // ---------------- UsagePatternModelService Tests ----------------
    @Test
    public void testCreateUsagePatternModel() {
        Bin bin = new Bin();
        bin.setId(1L);
        bin.setActive(true);

        UsagePatternModel model = new UsagePatternModel();
        model.setBin(bin);
        model.setAverageDailyUsage(5.0);
        model.setPeakUsage(10.0);
        model.setPatternType("weekday");

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(modelRepository.save(model)).thenReturn(model);

        UsagePatternModel result = modelService.create(model);
        assertNotNull(result);
        assertEquals(result.getAverageDailyUsage(), 5.0);
    }

    // ---------------- FillLevelRecordService Tests ----------------
    @Test
    public void testCreateFillLevelRecord() {
        Bin bin = new Bin();
        bin.setId(1L);

        FillLevelRecord record = new FillLevelRecord();
        record.setBin(bin);
        record.setRecordedAt(LocalDateTime.now());

        when(recordRepository.save(record)).thenReturn(record);

        FillLevelRecord saved = recordService.create(record);
        assertNotNull(saved);
        assertEquals(saved.getBin(), bin);
    }

    @Test
    public void testGetFillLevelRecordsByBin() {
        Bin bin = new Bin();
        bin.setId(1L);

        FillLevelRecord record = new FillLevelRecord();
        record.setBin(bin);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(recordRepository.findByBin(bin)).thenReturn(Collections.singletonList(record));

        assertEquals(recordService.getByBin(1L).size(), 1);
    }
}
