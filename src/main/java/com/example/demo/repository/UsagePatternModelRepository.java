package com.example.demo.repository;

import com.example.demo.model.UsagePatternModel;
import com.example.demo.model.Bin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.List;

public interface UsagePatternModelRepository extends JpaRepository<UsagePatternModel, Long> {
    Optional<UsagePatternModel> findByBin(Bin bin);
    List<UsagePatternModel> findAll();
}
