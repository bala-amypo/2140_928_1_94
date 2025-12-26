package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "fill_level_records")
public class FillLevelRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double fillPercentage;
    
    @Column(nullable = false)
    private LocalDateTime recordedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    // Constructors
    public FillLevelRecord() {
        this.recordedAt = LocalDateTime.now();
    }
    
    public FillLevelRecord(Double fillPercentage, Bin bin) {
        this.fillPercentage = fillPercentage;
        this.bin = bin;
        this.recordedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getFillPercentage() {
        return fillPercentage;
    }
    
    public void setFillPercentage(Double fillPercentage) {
        this.fillPercentage = fillPercentage;
    }
    
    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }
    
    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
    
    public Bin getBin() {
        return bin;
    }
    
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    
    @Override
    public String toString() {
        return "FillLevelRecord{" +
                "id=" + id +
                ", fillPercentage=" + fillPercentage +
                ", recordedAt=" + recordedAt +
                '}';
    }
}