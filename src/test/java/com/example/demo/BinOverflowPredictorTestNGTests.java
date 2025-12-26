package com.example.demo;

import com.example.demo.model.Bin;
import com.example.demo.model.OverflowPrediction;
import com.example.demo.repository.BinRepository;
import com.example.demo.repository.OverflowPredictionRepository;
import com.example.demo.service.impl.OverflowPredictionServiceImpl;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

public class BinOverflowPredictorTestNGTests {

    private OverflowPredictionServiceImpl service;
    private OverflowPredictionRepository predictionRepo;
    private BinRepository binRepo;

    @BeforeMethod
    public void setup() {
        predictionRepo = mock(OverflowPredictionRepository.class);
        binRepo = mock(BinRepository.class);
        service = new OverflowPredictionServiceImpl(predictionRepo, binRepo);
    }

    @Test
    public void testGeneratePrediction() {
        Bin bin = new Bin();
        bin.setId(1L);

        when(binRepo.findById(1L)).thenReturn(Optional.of(bin));
        when(predictionRepo.save(any(OverflowPrediction.class))).thenAnswer(i -> i.getArgument(0));

        OverflowPrediction prediction = service.generatePrediction(1L);

        assertEquals(prediction.getBin(), bin);
        assertEquals(prediction.getPredictedLevel(), 0.0);
    }
}
