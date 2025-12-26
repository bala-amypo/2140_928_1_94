package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Integer daysUntilFull;
    private LocalDate predictedFullDate;
    private LocalDateTime generatedAt;
    
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    
    @ManyToOne
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;
    
    // Getters and setters
}