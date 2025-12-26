package com.example.demo;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.FillLevelRecordRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.impl.BinServiceImpl;
import com.example.demo.service.impl.FillLevelRecordServiceImpl;
import com.example.demo.service.impl.OverflowPredictionServiceImpl;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

public class BinOverflowPredictorTestNGTests {

    @InjectMocks
    private BinServiceImpl binService;

    @InjectMocks
    private FillLevelRecordServiceImpl fillLevelService;

    @InjectMocks
    private OverflowPredictionServiceImpl overflowService;

    @Mock
    private BinRepository binRepository;

    @Mock
    private FillLevelRecordRepository fillLevelRepository;

    @Mock
    private OverflowPredictionRepository overflowRepository;

    @BeforeMethod
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateBin() {
        Bin bin = new Bin();
        bin.setIdentifier("BIN001");

        when(binRepository.save(any(Bin.class))).thenReturn(bin);

        Bin saved = binService.create(bin);
        assertNotNull(saved);
        assertEquals(saved.getIdentifier(), "BIN001");
    }

    @Test
    public void testCreateFillLevelRecord() {
        Bin bin = new Bin();
        bin.setId(1L);

        FillLevelRecord record = new FillLevelRecord();
        record.setBin(bin);

        when(fillLevelRepository.save(any(FillLevelRecord.class))).thenReturn(record);

        FillLevelRecord saved = fillLevelService.create(record);
        assertNotNull(saved);
        assertEquals(saved.getBin(), bin);
    }

    @Test
    public void testGenerateOverflowPrediction() {
        Bin bin = new Bin();
        bin.setId(1L);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));

        OverflowPrediction prediction = new OverflowPrediction();
        prediction.setBin(bin);

        when(overflowRepository.save(any(OverflowPrediction.class))).thenReturn(prediction);

        OverflowPrediction savedPrediction = overflowService.generatePrediction(1L);
        assertNotNull(savedPrediction);
        assertEquals(savedPrediction.getBin(), bin);
    }

    @Test
    public void testGetBinById() {
        Bin bin = new Bin();
        bin.setId(1L);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));

        Bin found = binService.getById(1L);
        assertNotNull(found);
        assertEquals(found.getId(), Long.valueOf(1));
    }

    @Test
    public void testGetFillLevelRecordsByBin() {
        Bin bin = new Bin();
        bin.setId(1L);

        FillLevelRecord record = new FillLevelRecord();
        record.setBin(bin);

        when(binRepository.findById(1L)).thenReturn(Optional.of(bin));
        when(fillLevelRepository.findByBin(bin)).thenReturn(List.of(record));

        List<FillLevelRecord> records = fillLevelService.getByBin(1L);
        assertNotNull(records);
        assertEquals(records.size(), 1);
        assertEquals(records.get(0).getBin(), bin);
    }
}
