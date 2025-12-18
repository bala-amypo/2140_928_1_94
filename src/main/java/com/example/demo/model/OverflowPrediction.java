package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Bin bin;

    @ManyToOne
    private Zone zone;

    private LocalDateTime predictedOverflowTime;
    private LocalDateTime createdAt;

    // getters and setters
}
