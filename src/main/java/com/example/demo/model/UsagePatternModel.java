package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Double avgDailyIncreaseWeekday;
    
    @Column(nullable = false)
    private Double avgDailyIncreaseWeekend;
    
    @Column(nullable = false)
    private LocalDateTime lastUpdated;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    // Constructors
    public UsagePatternModel() {
        this.lastUpdated = LocalDateTime.now();
    }
    
    public UsagePatternModel(Double avgDailyIncreaseWeekday, Double avgDailyIncreaseWeekend, Bin bin) {
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
        this.bin = bin;
        this.lastUpdated = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Double getAvgDailyIncreaseWeekday() {
        return avgDailyIncreaseWeekday;
    }
    
    public void setAvgDailyIncreaseWeekday(Double avgDailyIncreaseWeekday) {
        this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday;
    }
    
    public Double getAvgDailyIncreaseWeekend() {
        return avgDailyIncreaseWeekend;
    }
    
    public void setAvgDailyIncreaseWeekend(Double avgDailyIncreaseWeekend) {
        this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend;
    }
    
    public LocalDateTime getLastUpdated() {
        return lastUpdated;
    }
    
    public void setLastUpdated(LocalDateTime lastUpdated) {
        this.lastUpdated = lastUpdated;
    }
    
    public Bin getBin() {
        return bin;
    }
    
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    
    @Override
    public String toString() {
        return "UsagePatternModel{" +
                "id=" + id +
                ", avgDailyIncreaseWeekday=" + avgDailyIncreaseWeekday +
                ", avgDailyIncreaseWeekend=" + avgDailyIncreaseWeekend +
                ", lastUpdated=" + lastUpdated +
                '}';
    }
}