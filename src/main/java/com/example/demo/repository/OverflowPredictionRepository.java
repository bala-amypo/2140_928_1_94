package com.example.demo.repository;

import java.util.*;
import com.example.demo.model.*;

public interface OverflowPredictionRepository {
    OverflowPrediction save(OverflowPrediction p);
    List<OverflowPrediction> findLatestPredictionsForZone(Zone zone);
}
