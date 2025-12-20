package com.example.demo;

import com.example.demo.model.*;
import com.example.demo.repository.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class MockRepositories {
    
    public final MockBinRepository binRepository = new MockBinRepository();
    public final MockZoneRepository zoneRepository = new MockZoneRepository();
    public final MockFillLevelRecordRepository recordRepository = new MockFillLevelRecordRepository();
    public final MockUsagePatternModelRepository modelRepository = new MockUsagePatternModelRepository();
    public final MockOverflowPredictionRepository predictionRepository = new MockOverflowPredictionRepository();
    public final MockUserRepository userRepository = new MockUserRepository();
    
    public static class MockBinRepository implements BinRepository {
        private final Map<Long, Bin> bins = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends Bin> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            bins.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<Bin> findById(Long id) {
            return Optional.ofNullable(bins.get(id));
        }
        
        @Override
        public List<Bin> findAll() {
            return new ArrayList<>(bins.values());
        }
        
        @Override
        public Optional<Bin> findByIdentifier(String identifier) {
            return bins.values().stream()
                    .filter(bin -> bin.getIdentifier().equals(identifier))
                    .findFirst();
        }
        
        @Override
        public List<Bin> findByZoneAndActiveTrue(Zone zone) {
            return bins.values().stream()
                    .filter(bin -> bin.getZone().equals(zone) && bin.getActive())
                    .collect(Collectors.toList());
        }
        
        @Override public boolean existsById(Long id) { return bins.containsKey(id); }
        @Override public List<Bin> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return bins.size(); }
        @Override public void deleteById(Long id) { bins.remove(id); }
        @Override public void delete(Bin entity) { bins.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends Bin> entities) {}
        @Override public void deleteAll() { bins.clear(); }
        @Override public <S extends Bin> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends Bin> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends Bin> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<Bin> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public Bin getOne(Long id) { return findById(id).orElse(null); }
        @Override public Bin getById(Long id) { return findById(id).orElse(null); }
        @Override public Bin getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends Bin> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends Bin> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends Bin> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends Bin> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends Bin> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends Bin> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends Bin, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<Bin> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<Bin> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
    
    public static class MockZoneRepository implements ZoneRepository {
        private final Map<Long, Zone> zones = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends Zone> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            zones.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<Zone> findById(Long id) {
            return Optional.ofNullable(zones.get(id));
        }
        
        @Override
        public List<Zone> findAll() {
            return new ArrayList<>(zones.values());
        }
        
        @Override
        public Optional<Zone> findByZoneName(String name) {
            return zones.values().stream()
                    .filter(zone -> zone.getZoneName().equals(name))
                    .findFirst();
        }
        
        @Override public boolean existsById(Long id) { return zones.containsKey(id); }
        @Override public List<Zone> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return zones.size(); }
        @Override public void deleteById(Long id) { zones.remove(id); }
        @Override public void delete(Zone entity) { zones.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends Zone> entities) {}
        @Override public void deleteAll() { zones.clear(); }
        @Override public <S extends Zone> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends Zone> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends Zone> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<Zone> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public Zone getOne(Long id) { return findById(id).orElse(null); }
        @Override public Zone getById(Long id) { return findById(id).orElse(null); }
        @Override public Zone getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends Zone> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends Zone> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends Zone> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends Zone> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends Zone> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends Zone> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends Zone, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<Zone> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<Zone> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
    
    public static class MockFillLevelRecordRepository implements FillLevelRecordRepository {
        private final Map<Long, FillLevelRecord> records = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends FillLevelRecord> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            records.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<FillLevelRecord> findById(Long id) {
            return Optional.ofNullable(records.get(id));
        }
        
        @Override
        public List<FillLevelRecord> findAll() {
            return new ArrayList<>(records.values());
        }
        
        @Override
        public List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin) {
            return records.values().stream()
                    .filter(record -> record.getBin().equals(bin))
                    .sorted((r1, r2) -> r2.getRecordedAt().compareTo(r1.getRecordedAt()))
                    .collect(Collectors.toList());
        }
        
        @Override
        public Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin) {
            return findByBinOrderByRecordedAtDesc(bin).stream().findFirst();
        }
        
        @Override
        public List<FillLevelRecord> findByBinAndRecordedAtBetween(Bin bin, java.time.LocalDateTime start, java.time.LocalDateTime end) {
            return records.values().stream()
                    .filter(record -> record.getBin().equals(bin))
                    .collect(Collectors.toList());
        }
        
        @Override public boolean existsById(Long id) { return records.containsKey(id); }
        @Override public List<FillLevelRecord> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return records.size(); }
        @Override public void deleteById(Long id) { records.remove(id); }
        @Override public void delete(FillLevelRecord entity) { records.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends FillLevelRecord> entities) {}
        @Override public void deleteAll() { records.clear(); }
        @Override public <S extends FillLevelRecord> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends FillLevelRecord> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends FillLevelRecord> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<FillLevelRecord> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public FillLevelRecord getOne(Long id) { return findById(id).orElse(null); }
        @Override public FillLevelRecord getById(Long id) { return findById(id).orElse(null); }
        @Override public FillLevelRecord getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends FillLevelRecord> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends FillLevelRecord> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends FillLevelRecord> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends FillLevelRecord> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends FillLevelRecord> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends FillLevelRecord> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends FillLevelRecord, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<FillLevelRecord> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<FillLevelRecord> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
    
    public static class MockUsagePatternModelRepository implements UsagePatternModelRepository {
        private final Map<Long, UsagePatternModel> models = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends UsagePatternModel> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            models.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<UsagePatternModel> findById(Long id) {
            return Optional.ofNullable(models.get(id));
        }
        
        @Override
        public List<UsagePatternModel> findAll() {
            return new ArrayList<>(models.values());
        }
        
        @Override
        public Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin) {
            return models.values().stream()
                    .filter(model -> model.getBin().equals(bin))
                    .max(Comparator.comparing(UsagePatternModel::getLastUpdated));
        }
        
        @Override public boolean existsById(Long id) { return models.containsKey(id); }
        @Override public List<UsagePatternModel> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return models.size(); }
        @Override public void deleteById(Long id) { models.remove(id); }
        @Override public void delete(UsagePatternModel entity) { models.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends UsagePatternModel> entities) {}
        @Override public void deleteAll() { models.clear(); }
        @Override public <S extends UsagePatternModel> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends UsagePatternModel> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends UsagePatternModel> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<UsagePatternModel> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public UsagePatternModel getOne(Long id) { return findById(id).orElse(null); }
        @Override public UsagePatternModel getById(Long id) { return findById(id).orElse(null); }
        @Override public UsagePatternModel getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends UsagePatternModel> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends UsagePatternModel> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends UsagePatternModel> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends UsagePatternModel> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends UsagePatternModel> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends UsagePatternModel> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends UsagePatternModel, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<UsagePatternModel> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<UsagePatternModel> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
    
    public static class MockOverflowPredictionRepository implements OverflowPredictionRepository {
        private final Map<Long, OverflowPrediction> predictions = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends OverflowPrediction> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            predictions.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<OverflowPrediction> findById(Long id) {
            return Optional.ofNullable(predictions.get(id));
        }
        
        @Override
        public List<OverflowPrediction> findAll() {
            return new ArrayList<>(predictions.values());
        }
        
        @Override
        public List<OverflowPrediction> findLatestPredictionsForZone(Zone zone) {
            return predictions.values().stream()
                    .filter(p -> p.getBin().getZone().equals(zone))
                    .collect(Collectors.toList());
        }
        
        @Override public boolean existsById(Long id) { return predictions.containsKey(id); }
        @Override public List<OverflowPrediction> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return predictions.size(); }
        @Override public void deleteById(Long id) { predictions.remove(id); }
        @Override public void delete(OverflowPrediction entity) { predictions.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends OverflowPrediction> entities) {}
        @Override public void deleteAll() { predictions.clear(); }
        @Override public <S extends OverflowPrediction> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends OverflowPrediction> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends OverflowPrediction> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<OverflowPrediction> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public OverflowPrediction getOne(Long id) { return findById(id).orElse(null); }
        @Override public OverflowPrediction getById(Long id) { return findById(id).orElse(null); }
        @Override public OverflowPrediction getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends OverflowPrediction> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends OverflowPrediction> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends OverflowPrediction> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends OverflowPrediction> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends OverflowPrediction> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends OverflowPrediction> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends OverflowPrediction, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<OverflowPrediction> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<OverflowPrediction> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
    
    public static class MockUserRepository implements UserRepository {
        private final Map<Long, User> users = new HashMap<>();
        private final AtomicLong idGenerator = new AtomicLong(1);
        
        @Override
        public <S extends User> S save(S entity) {
            if (entity.getId() == null) {
                entity.setId(idGenerator.getAndIncrement());
            }
            users.put(entity.getId(), entity);
            return entity;
        }
        
        @Override
        public Optional<User> findById(Long id) {
            return Optional.ofNullable(users.get(id));
        }
        
        @Override
        public List<User> findAll() {
            return new ArrayList<>(users.values());
        }
        
        @Override
        public Optional<User> findByEmail(String email) {
            return users.values().stream()
                    .filter(user -> user.getEmail().equals(email))
                    .findFirst();
        }
        
        @Override public boolean existsById(Long id) { return users.containsKey(id); }
        @Override public List<User> findAllById(Iterable<Long> ids) { return new ArrayList<>(); }
        @Override public long count() { return users.size(); }
        @Override public void deleteById(Long id) { users.remove(id); }
        @Override public void delete(User entity) { users.remove(entity.getId()); }
        @Override public void deleteAllById(Iterable<? extends Long> ids) {}
        @Override public void deleteAll(Iterable<? extends User> entities) {}
        @Override public void deleteAll() { users.clear(); }
        @Override public <S extends User> List<S> saveAll(Iterable<S> entities) { return null; }
        @Override public void flush() {}
        @Override public <S extends User> S saveAndFlush(S entity) { return save(entity); }
        @Override public <S extends User> List<S> saveAllAndFlush(Iterable<S> entities) { return null; }
        @Override public void deleteAllInBatch(Iterable<User> entities) {}
        @Override public void deleteAllByIdInBatch(Iterable<Long> ids) {}
        @Override public void deleteAllInBatch() {}
        @Override public User getOne(Long id) { return findById(id).orElse(null); }
        @Override public User getById(Long id) { return findById(id).orElse(null); }
        @Override public User getReferenceById(Long id) { return findById(id).orElse(null); }
        @Override public <S extends User> Optional<S> findOne(org.springframework.data.domain.Example<S> example) { return Optional.empty(); }
        @Override public <S extends User> List<S> findAll(org.springframework.data.domain.Example<S> example) { return null; }
        @Override public <S extends User> List<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Sort sort) { return null; }
        @Override public <S extends User> org.springframework.data.domain.Page<S> findAll(org.springframework.data.domain.Example<S> example, org.springframework.data.domain.Pageable pageable) { return null; }
        @Override public <S extends User> long count(org.springframework.data.domain.Example<S> example) { return 0; }
        @Override public <S extends User> boolean exists(org.springframework.data.domain.Example<S> example) { return false; }
        @Override public <S extends User, R> R findBy(org.springframework.data.domain.Example<S> example, java.util.function.Function<org.springframework.data.repository.query.FluentQuery.FetchableFluentQuery<S>, R> queryFunction) { return null; }
        @Override public List<User> findAll(org.springframework.data.domain.Sort sort) { return findAll(); }
        @Override public org.springframework.data.domain.Page<User> findAll(org.springframework.data.domain.Pageable pageable) { return null; }
    }
}