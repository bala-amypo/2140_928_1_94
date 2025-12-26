package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double avgDailyIncreaseWeekday;
    private Double avgDailyIncreaseWeekend;
    private LocalDateTime lastUpdated;
    
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    
    // Getters and setters
}