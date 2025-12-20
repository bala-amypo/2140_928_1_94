package com.example.demo;

import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.service.impl.*;
import org.testng.annotations.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import static org.testng.Assert.*;

@Listeners(TestResultListener.class)
public class BinOverflowPredictorTestNGTests {
    
    private MockRepositories mockRepos;
    private ZoneServiceImpl zoneService;
    private BinServiceImpl binService;
    private FillLevelRecordServiceImpl recordService;
    private UsagePatternModelServiceImpl modelService;
    private OverflowPredictionServiceImpl predictionService;
    
    @BeforeMethod
    public void setUp() {
        mockRepos = new MockRepositories();
        zoneService = new ZoneServiceImpl(mockRepos.zoneRepository);
        binService = new BinServiceImpl(mockRepos.binRepository, mockRepos.zoneRepository);
        recordService = new FillLevelRecordServiceImpl(mockRepos.recordRepository, mockRepos.binRepository);
        modelService = new UsagePatternModelServiceImpl(mockRepos.modelRepository, mockRepos.binRepository);
        predictionService = new OverflowPredictionServiceImpl(mockRepos.binRepository, mockRepos.recordRepository, 
                                                            mockRepos.modelRepository, mockRepos.predictionRepository, mockRepos.zoneRepository);
    }
    
    @Test(priority = 1)
    public void testCreateZone() {
        Zone zone = new Zone("Downtown", "Central business district", true);
        Zone created = zoneService.createZone(zone);
        
        assertNotNull(created.getId());
        assertEquals(created.getZoneName(), "Downtown");
        assertTrue(created.getActive());
    }
    
    @Test(priority = 2, dependsOnMethods = "testCreateZone")
    public void testCreateBin() {
        Zone zone = zoneService.createZone(new Zone("TestZone", "Test", true));
        Bin bin = new Bin("BIN001", "Main Street", 40.7128, -74.0060, zone, 100.0, true, 
                         new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        
        Bin created = binService.createBin(bin);
        
        assertNotNull(created.getId());
        assertEquals(created.getIdentifier(), "BIN001");
        assertEquals(created.getCapacityLiters(), 100.0);
        assertTrue(created.getActive());
    }
    
    @Test(priority = 3)
    public void testInvalidBinCapacity() {
        Zone zone = zoneService.createZone(new Zone("TestZone2", "Test", true));
        Bin bin = new Bin("BIN002", "Test St", 40.0, -74.0, zone, -10.0, true, 
                         new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()));
        
        assertThrows(BadRequestException.class, () -> binService.createBin(bin));
    }
    
    @Test(priority = 4, dependsOnMethods = "testCreateBin")
    public void testCreateFillRecord() {
        Zone zone = zoneService.createZone(new Zone("RecordZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN003", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        FillLevelRecord record = new FillLevelRecord(bin, 75.0, new Timestamp(System.currentTimeMillis()), false);
        FillLevelRecord created = recordService.createRecord(record);
        
        assertNotNull(created.getId());
        assertEquals(created.getFillPercentage(), 75.0);
        assertEquals(created.getBin().getId(), bin.getId());
    }
    
    @Test(priority = 5)
    public void testInvalidFillPercentage() {
        Zone zone = zoneService.createZone(new Zone("InvalidZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN004", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        FillLevelRecord record = new FillLevelRecord(bin, 150.0, new Timestamp(System.currentTimeMillis()), false);
        
        assertThrows(BadRequestException.class, () -> recordService.createRecord(record));
    }
    
    @Test(priority = 6, dependsOnMethods = "testCreateBin")
    public void testCreateUsageModel() {
        Zone zone = zoneService.createZone(new Zone("ModelZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN005", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        UsagePatternModel model = new UsagePatternModel(bin, 15.0, 10.0, new Timestamp(System.currentTimeMillis()));
        UsagePatternModel created = modelService.createModel(model);
        
        assertNotNull(created.getId());
        assertEquals(created.getAvgDailyIncreaseWeekday(), 15.0);
        assertEquals(created.getAvgDailyIncreaseWeekend(), 10.0);
    }
    
    @Test(priority = 7)
    public void testNegativeDailyIncrease() {
        Zone zone = zoneService.createZone(new Zone("NegativeZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN006", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        UsagePatternModel model = new UsagePatternModel(bin, -5.0, 10.0, new Timestamp(System.currentTimeMillis()));
        
        assertThrows(BadRequestException.class, () -> modelService.createModel(model));
    }
    
    @Test(priority = 8, dependsOnMethods = {"testCreateFillRecord", "testCreateUsageModel"})
    public void testGeneratePrediction() {
        Zone zone = zoneService.createZone(new Zone("PredictionZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN007", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        recordService.createRecord(new FillLevelRecord(bin, 50.0, new Timestamp(System.currentTimeMillis()), false));
        modelService.createModel(new UsagePatternModel(bin, 10.0, 8.0, new Timestamp(System.currentTimeMillis())));
        
        OverflowPrediction prediction = predictionService.generatePrediction(bin.getId());
        
        assertNotNull(prediction.getId());
        assertNotNull(prediction.getPredictedFullDate());
        assertTrue(prediction.getDaysUntilFull() >= 0);
    }
    
    @Test(priority = 9, dependsOnMethods = "testCreateZone")
    public void testDeactivateZone() {
        Zone zone = zoneService.createZone(new Zone("DeactivateZone", "Test", true));
        assertTrue(zone.getActive());
        
        zoneService.deactivateZone(zone.getId());
        Zone deactivated = zoneService.getZoneById(zone.getId());
        
        assertFalse(deactivated.getActive());
    }
    
    @Test(priority = 10, dependsOnMethods = "testCreateBin")
    public void testDeactivateBin() {
        Zone zone = zoneService.createZone(new Zone("DeactivateBinZone", "Test", true));
        Bin bin = binService.createBin(new Bin("BIN008", "Test", 40.0, -74.0, zone, 100.0, true, 
                                             new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        assertTrue(bin.getActive());
        
        binService.deactivateBin(bin.getId());
        Bin deactivated = binService.getBinById(bin.getId());
        
        assertFalse(deactivated.getActive());
    }
    
    @Test(priority = 11)
    public void testResourceNotFound() {
        assertThrows(ResourceNotFoundException.class, () -> binService.getBinById(999L));
        assertThrows(ResourceNotFoundException.class, () -> zoneService.getZoneById(999L));
    }
    
    @Test(priority = 12)
    public void testUniqueConstraints() {
        Zone zone1 = zoneService.createZone(new Zone("UniqueZone", "Test", true));
        
        // Test unique zone name
        assertThrows(BadRequestException.class, () -> 
            zoneService.createZone(new Zone("UniqueZone", "Duplicate", true)));
        
        // Test unique bin identifier
        Bin bin1 = binService.createBin(new Bin("UNIQUE001", "Test", 40.0, -74.0, zone1, 100.0, true, 
                                              new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis())));
        
        assertThrows(BadRequestException.class, () -> 
            binService.createBin(new Bin("UNIQUE001", "Duplicate", 40.0, -74.0, zone1, 100.0, true, 
                                       new Timestamp(System.currentTimeMillis()), new Timestamp(System.currentTimeMillis()))));
    }
}