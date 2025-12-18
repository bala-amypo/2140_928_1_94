package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    private double avgDailyIncreaseWeekday;
    private double avgDailyIncreaseWeekend;

    private LocalDateTime lastUpdated;

    // getters and setters
}
