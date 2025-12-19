package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.FillLevelRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FillLevelRecordRepository extends JpaRepository<FillLevelRecord, Long> {

    List<FillLevelRecord> findByBinOrderByRecordedAtDesc(Bin bin);
}
