package com.example.demo.repository;

import com.example.demo.model.Bin;
import com.example.demo.model.UsagePatternModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsagePatternModelRepository extends JpaRepository<UsagePatternModel, Long> {
    Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
}