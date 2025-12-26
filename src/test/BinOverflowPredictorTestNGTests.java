package com.example.demo;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.impl.*;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

public class BinOverflowPredictorTestNGTests {

    private BinRepository binRepository;
    private FillLevelRecordRepository recordRepository;
    private UsagePatternModelRepository modelRepository;
    private OverflowPredictionRepository predictionRepository;
    private ZoneRepository zoneRepository;

    private BinServiceImpl binService;
    private FillLevelRecordServiceImpl recordService;
    private UsagePatternModelServiceImpl modelService;
    private OverflowPredictionServiceImpl predictionService;
    private ZoneServiceImpl zoneService;

    private JwtTokenProvider jwtTokenProvider;
    private CustomUserDetailsService userDetailsService;

    @BeforeClass
    public void setUp() {
        binRepository = Mockito.mock(BinRepository.class);
        recordRepository = Mockito.mock(FillLevelRecordRepository.class);
        modelRepository = Mockito.mock(UsagePatternModelRepository.class);
        predictionRepository = Mockito.mock(OverflowPredictionRepository.class);
        zoneRepository = Mockito.mock(ZoneRepository.class);

        binService = new BinServiceImpl(binRepository, zoneRepository);
        recordService = new FillLevelRecordServiceImpl(recordRepository, binRepository);
        modelService = new UsagePatternModelServiceImpl(modelRepository, binRepository);
        predictionService = new OverflowPredictionServiceImpl(
                binRepository, recordRepository, modelRepository, predictionRepository, zoneRepository);
        zoneService = new ZoneServiceImpl(zoneRepository);

        jwtTokenProvider = new JwtTokenProvider("VerySecretKeyForJwtDemo1234567890");
        userDetailsService = new CustomUserDetailsService();
    }

    private void setId(Object entity, Long id) {
        try {
            Field field = entity.getClass().getDeclaredField("id");
            field.setAccessible(true);
            field.set(entity, id);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set id via reflection", e);
        }
    }

    @Test
    public void testBinCreation() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);
        zone.setZoneName("Downtown");

        Bin bin = new Bin();
        bin.setIdentifier("BIN-001");
        bin.setCapacityLiters(100.0);
        bin.setLatitude(10.0);
        bin.setLongitude(20.0);
        bin.setZone(zone);

        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(binRepository.save(any(Bin.class))).thenAnswer(inv -> {
            Bin b = inv.getArgument(0);
            if (b.getId() == null) {
                setId(b, 100L);
            }
            return b;
        });

        Bin created = binService.createBin(bin);
        Assert.assertEquals(created.getIdentifier(), "BIN-001");
    }

    @Test
    public void testBinInvalidCapacity() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);

        Bin bin = new Bin();
        bin.setIdentifier("BIN-002");
        bin.setCapacityLiters(0.0);
        bin.setZone(zone);

        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(zone));

        try {
            binService.createBin(bin);
            Assert.fail("Should throw BadRequestException");
        } catch (BadRequestException ex) {
            Assert.assertNotNull(ex.getMessage());
        }
    }

    @Test
    public void testCreateZone() {
        Zone zone = new Zone();
        zone.setZoneName("Riverside");
        when(zoneRepository.save(any(Zone.class))).thenAnswer(inv -> {
            Zone z = inv.getArgument(0);
            if (z.getId() == null) {
                setId(z, 10L);
            }
            return z;
        });
        Zone created = zoneService.createZone(zone);
        Assert.assertTrue(created.getActive());
    }

    @Test
    public void testGenerateJwtToken() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("admin@city.com", "admin123", Collections.emptyList());
        String token = jwtTokenProvider.generateToken(auth, 1L, "ADMIN", "admin@city.com");
        Assert.assertNotNull(token);
    }
}