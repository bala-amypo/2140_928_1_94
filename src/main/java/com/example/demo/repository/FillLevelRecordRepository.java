package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.*;
import com.example.demo.model.*;

public interface FillLevelRecordRepository {
    FillLevelRecord save(FillLevelRecord r);
    Optional<FillLevelRecord> findById(Long id);
    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
    Optional<FillLevelRecord> findTop1ByBinOrderByRecordedAtDesc(Bin bin);
    List<FillLevelRecord> findByBinAndRecordedAtBetween(Bin bin, LocalDateTime s, LocalDateTime e);
}
