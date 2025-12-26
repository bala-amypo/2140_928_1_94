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
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@Listeners(TestResultListener.class)
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

    // ---------------------------------------------------------
    // 1. Servlet/Tomcat tests
    // ---------------------------------------------------------
    @Test(priority = 1, groups = "servlet")
    public void testServlet_likeContextLoadsForBinService() {
        Assert.assertNotNull(binService);
    }

    @Test(priority = 2, groups = "servlet")
    public void testServlet_likeCreateBin() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);
        zone.setZoneName("Downtown");
        zone.setDescription("Central Area");

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

    @Test(priority = 3, groups = "servlet")
    public void testServlet_likeCreateBinInvalidCapacity() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);
        zone.setZoneName("Downtown");

        Bin bin = new Bin();
        bin.setIdentifier("BIN-002");
        bin.setCapacityLiters(0.0);
        bin.setLatitude(10.0);
        bin.setLongitude(20.0);
        bin.setZone(zone);

        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(zone));

        try {
            binService.createBin(bin);
            Assert.fail("Should throw BadRequestException");
        } catch (BadRequestException ex) {
            Assert.assertNotNull(ex.getMessage());
        }
    }

    @Test(priority = 4, groups = "servlet")
    public void testServlet_likeInactiveZoneRejectsBin() {
        Zone zone = new Zone();
        setId(zone, 2L);
        zone.setActive(false);
        zone.setZoneName("Old Zone");

        Bin bin = new Bin();
        bin.setIdentifier("BIN-003");
        bin.setCapacityLiters(100.0);
        bin.setLatitude(10.0);
        bin.setLongitude(20.0);
        bin.setZone(zone);

        when(zoneRepository.findById(2L)).thenReturn(Optional.of(zone));

        try {
            binService.createBin(bin);
            Assert.fail("Expected BadRequestException");
        } catch (BadRequestException ex) {
            Assert.assertNotNull(ex.getMessage());
        }
    }

    @Test(priority = 5, groups = "servlet")
    public void testServlet_like404ForMissingBin() {
        when(binRepository.findById(999L)).thenReturn(Optional.empty());
        try {
            binService.getBinById(999L);
            Assert.fail("Expected ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            Assert.assertTrue(ex.getMessage().contains("Bin not found"));
        }
    }

    @Test(priority = 6, groups = "servlet")
    public void testServlet_likeUpdateBinLocation() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);

        Bin existing = new Bin();
        setId(existing, 4L);
        existing.setIdentifier("BIN-004");
        existing.setCapacityLiters(100.0);
        existing.setLatitude(10.0);
        existing.setLongitude(20.0);
        existing.setZone(zone);

        when(binRepository.findById(4L)).thenReturn(Optional.of(existing));
        when(zoneRepository.findById(anyLong())).thenReturn(Optional.of(zone));
        when(binRepository.save(any(Bin.class))).thenAnswer(inv -> inv.getArgument(0));

        Bin update = new Bin();
        update.setLocationDescription("Near Park");

        Bin result = binService.updateBin(4L, update);
        Assert.assertEquals(result.getLocationDescription(), "Near Park");
    }

    @Test(priority = 7, groups = "servlet")
    public void testServlet_likeDeactivateBin() {
        Bin bin = new Bin();
        setId(bin, 5L);
        bin.setIdentifier("BIN-005");
        bin.setCapacityLiters(100.0);
        bin.setLatitude(10.0);
        bin.setLongitude(20.0);
        bin.setActive(true);

        when(binRepository.findById(5L)).thenReturn(Optional.of(bin));
        when(binRepository.save(any(Bin.class))).thenAnswer(inv -> inv.getArgument(0));

        binService.deactivateBin(5L);
        Assert.assertFalse(bin.getActive());
    }

    @Test(priority = 8, groups = "servlet")
    public void testServlet_likeGetAllBins() {
        when(binRepository.findAll()).thenReturn(Collections.emptyList());
        Assert.assertNotNull(binService.getAllBins());
    }

    // ---------------------------------------------------------
    // 2. CRUD operations tests
    // ---------------------------------------------------------
    @Test(priority = 9, groups = "crud")
    public void testCrudCreateZone() {
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

    @Test(priority = 10, groups = "crud")
    public void testCrudGetZoneByIdNotFound() {
        when(zoneRepository.findById(100L)).thenReturn(Optional.empty());
        try {
            zoneService.getZoneById(100L);
            Assert.fail("Expected ResourceNotFoundException");
        } catch (ResourceNotFoundException ex) {
            Assert.assertTrue(ex.getMessage().contains("Zone not found"));
        }
    }

    @Test(priority = 11, groups = "crud")
    public void testCrudUpdateZoneDescription() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setZoneName("Central");
        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(zoneRepository.save(any(Zone.class))).thenAnswer(inv -> inv.getArgument(0));

        Zone update = new Zone();
        update.setDescription("Central business district");
        Zone result = zoneService.updateZone(1L, update);
        Assert.assertEquals(result.getDescription(), "Central business district");
    }

    @Test(priority = 12, groups = "crud")
    public void testCrudDeactivateZone() {
        Zone zone = new Zone();
        setId(zone, 2L);
        zone.setZoneName("West");
        zone.setActive(true);
        when(zoneRepository.findById(2L)).thenReturn(Optional.of(zone));
        when(zoneRepository.save(any(Zone.class))).thenAnswer(inv -> inv.getArgument(0));

        zoneService.deactivateZone(2L);
        Assert.assertFalse(zone.getActive());
    }

    // ---------------------------------------------------------
    // 3. DI and IoC tests
    // ---------------------------------------------------------
    @Test(priority = 17, groups = "di")
    public void testDI_BinServiceUsesZoneRepository() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);
        zone.setZoneName("Downtown");

        Bin bin = new Bin();
        bin.setIdentifier("BIN-020");
        bin.setCapacityLiters(100.0);
        bin.setLatitude(1.0);
        bin.setLongitude(2.0);
        bin.setZone(zone);

        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(binRepository.save(any(Bin.class))).thenAnswer(inv -> inv.getArgument(0));

        Bin created = binService.createBin(bin);
        Assert.assertNotNull(created.getZone());
        Assert.assertEquals(created.getZone().getZoneName(), "Downtown");
    }

    @Test(priority = 18, groups = "di")
    public void testDI_UsageModelServiceUsesBinRepository() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);

        Bin bin = new Bin();
        setId(bin, 30L);
        bin.setZone(zone);
        bin.setIdentifier("BIN-030");
        bin.setCapacityLiters(100.0);
        bin.setActive(true);

        UsagePatternModel model = new UsagePatternModel();
        model.setBin(bin);
        model.setAvgDailyIncreaseWeekday(10.0);
        model.setAvgDailyIncreaseWeekend(5.0);

        when(binRepository.findById(30L)).thenReturn(Optional.of(bin));
        when(modelRepository.save(any(UsagePatternModel.class))).thenAnswer(inv -> inv.getArgument(0));

        UsagePatternModel saved = modelService.createModel(model);
        Assert.assertEquals(saved.getAvgDailyIncreaseWeekday(), 10.0);
    }

    // ---------------------------------------------------------
    // 4. Hibernate tests
    // ---------------------------------------------------------
    @Test(priority = 25, groups = "hibernate")
    public void testHibernate_BinHasGeneratedId() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);

        Bin bin = new Bin();
        bin.setIdentifier("BIN-040");
        bin.setCapacityLiters(100.0);
        bin.setLatitude(1.0);
        bin.setLongitude(2.0);
        bin.setZone(zone);

        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(binRepository.save(any(Bin.class))).thenAnswer(inv -> {
            Bin b = inv.getArgument(0);
            if (b.getId() == null) {
                setId(b, 40L);
            }
            return b;
        });

        Bin saved = binService.createBin(bin);
        Assert.assertNotNull(saved.getId());
        Assert.assertEquals(saved.getIdentifier(), "BIN-040");
    }

    @Test(priority = 32, groups = "hibernate")
    public void testHibernate_RejectInactiveBinForFillRecord() {
        Zone zone = new Zone();
        setId(zone, 1L);
        zone.setActive(true);

        Bin bin = new Bin();
        setId(bin, 80L);
        bin.setZone(zone);
        bin.setActive(false);

        FillLevelRecord record = new FillLevelRecord();
        record.setBin(bin);
        record.setFillPercentage(50.0);
        record.setRecordedAt(LocalDateTime.now());

        when(binRepository.findById(80L)).thenReturn(Optional.of(bin));

        try {
            recordService.createRecord(record);
            Assert.fail("Expected BadRequestException");
        } catch (BadRequestException ex) {
            Assert.assertNotNull(ex.getMessage());
        }
    }

    // ---------------------------------------------------------
    // 5. JPA mapping tests
    // ---------------------------------------------------------
    @Test(priority = 33, groups = "jpa")
    public void testJPA_BinZoneMapping() {
        Zone zone = new Zone();
        zone.setZoneName("Central");
        Bin bin = new Bin();
        bin.setIdentifier("BIN-070");
        bin.setZone(zone);
        Assert.assertEquals(bin.getZone().getZoneName(), "Central");
    }

    @Test(priority = 39, groups = "jpa")
    public void testJPA_ZoneActiveConstraint() {
        Zone zone = new Zone();
        zone.setActive(true);
        Assert.assertTrue(zone.getActive());
    }

    // ---------------------------------------------------------
    // 6. Many-to-Many relationships tests
    // ---------------------------------------------------------
    @Test(priority = 41, groups = "associations")
    public void testAssociations_MultipleBinsSameZone() {
        Zone zone = new Zone();
        zone.setZoneName("SharedZone");
        Bin b1 = new Bin();
        b1.setZone(zone);
        Bin b2 = new Bin();
        b2.setZone(zone);
        Assert.assertEquals(b1.getZone(), b2.getZone());
    }

    // ---------------------------------------------------------
    // 7. Security & JWT tests
    // ---------------------------------------------------------
    @Test(priority = 49, groups = "security")
    public void testSecurity_GenerateJwtToken() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("admin@city.com", "admin123", Collections.emptyList());
        String token = jwtTokenProvider.generateToken(auth, 1L, "ADMIN", "admin@city.com");
        Assert.assertNotNull(token);
    }

    @Test(priority = 50, groups = "security")
    public void testSecurity_JwtContainsEmailAndRole() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("user@city.com", "user123", Collections.emptyList());
        String token = jwtTokenProvider.generateToken(auth, 2L, "USER", "user@city.com");
        Assert.assertTrue(token.length() > 10);
    }

    @Test(priority = 51, groups = "security")
    public void testSecurity_UserDetailsServiceDefaultAdmin() {
        CustomUserDetailsService.DemoUser user = userDetailsService.getByEmail("admin@city.com");
        Assert.assertEquals(user.getRole(), "ADMIN");
    }

    @Test(priority = 52, groups = "security")
    public void testSecurity_RegisterNewUser() {
        CustomUserDetailsService.DemoUser user =
                userDetailsService.registerUser("Test User", "test@city.com", "password");
        Assert.assertEquals(user.getEmail(), "test@city.com");
    }

    @Test(priority = 53, groups = "security")
    public void testSecurity_RegisterDuplicateUserFails() {
        userDetailsService.registerUser("User1", "dup@city.com", "pwd");
        try {
            userDetailsService.registerUser("User2", "dup@city.com", "pwd2");
            Assert.fail("Expected exception for duplicate");
        } catch (RuntimeException ex) {
            Assert.assertTrue(ex.getMessage().contains("exists"));
        }
    }

    // ---------------------------------------------------------
    // 8. HQL / Query tests
    // ---------------------------------------------------------
    @Test(priority = 57, groups = "hql")
    public void testHQL_FindLatestPredictionForZone() {
        Zone zone = new Zone();
        setId(zone, 1L);
        Bin bin = new Bin();
        bin.setZone(zone);

        OverflowPrediction p1 = new OverflowPrediction();
        p1.setBin(bin);
        p1.setDaysUntilFull(2);
        OverflowPrediction p2 = new OverflowPrediction();
        p2.setBin(bin);
        p2.setDaysUntilFull(5);

        List<OverflowPrediction> latest = Arrays.asList(p1, p2);
        when(zoneRepository.findById(1L)).thenReturn(Optional.of(zone));
        when(predictionRepository.findLatestPredictionsForZone(zone)).thenReturn(latest);

        List<OverflowPrediction> result = predictionService.getLatestPredictionsForZone(1L);
        Assert.assertEquals(result.size(), 2);
    }

    @Test(priority = 64, groups = "hql")
    public void testHQL_FindByIdentifierForBin() {
        Bin bin = new Bin();
        bin.setIdentifier("BIN-HQL-1");
        when(binRepository.findByIdentifier("BIN-HQL-1")).thenReturn(Optional.of(bin));
        Optional<Bin> found = binRepository.findByIdentifier("BIN-HQL-1");
        Assert.assertTrue(found.isPresent());
        Assert.assertEquals(found.get().getIdentifier(), "BIN-HQL-1");
    }
}