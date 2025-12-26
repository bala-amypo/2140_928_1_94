package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private Double fillPercentage;
    private LocalDateTime recordedAt;
    
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    
    // Getters and setters
}