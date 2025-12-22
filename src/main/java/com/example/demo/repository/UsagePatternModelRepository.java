package com.example.demo.repository;

import com.example.demo.model.UsagePatternModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsagePatternModelRepository
        extends JpaRepository<UsagePatternModel, Long> {

    // ✅ ADD THIS LINE
    List<UsagePatternModel> findByBinId(Long binId);
}
