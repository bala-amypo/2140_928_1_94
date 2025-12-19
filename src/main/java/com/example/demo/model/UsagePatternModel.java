package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Bin bin;

    private double avgDailyIncreaseWeekday;
    private double avgDailyIncreaseWeekend;
    private LocalDateTime lastUpdated = LocalDateTime.now();

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public double getAvgDailyIncreaseWeekday() { return avgDailyIncreaseWeekday; }
    public void setAvgDailyIncreaseWeekday(double avgDailyIncreaseWeekday) { this.avgDailyIncreaseWeekday = avgDailyIncreaseWeekday; }

    public double getAvgDailyIncreaseWeekend() { return avgDailyIncreaseWeekend; }
    public void setAvgDailyIncreaseWeekend(double avgDailyIncreaseWeekend) { this.avgDailyIncreaseWeekend = avgDailyIncreaseWeekend; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public void setLastUpdated(LocalDateTime lastUpdated) { this.lastUpdated = lastUpdated; }
}
