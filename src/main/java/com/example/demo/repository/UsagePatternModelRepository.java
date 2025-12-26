package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface UsagePatternModelRepository {
    UsagePatternModel save(UsagePatternModel m);
    Optional<UsagePatternModel> findById(Long id);
    Optional<UsagePatternModel> findTop1ByBinOrderByLastUpdatedDesc(Bin bin);
}
