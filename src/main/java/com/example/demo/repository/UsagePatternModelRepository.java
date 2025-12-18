package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsagePatternModelRepository extends JpaRepository<UsagePatternModel, Long> {
    UsagePatternModel findTopByBinOrderByLastUpdatedDesc(Bin bin);
}
