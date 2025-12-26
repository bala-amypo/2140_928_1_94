package com.example.demo.model;

import java.time.LocalDateTime;

public class UsagePatternModel {
    private Long id;
    private Bin bin;
    private Double avgDailyIncreaseWeekday;
    private Double avgDailyIncreaseWeekend;
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public Double getAvgDailyIncreaseWeekday() { return avgDailyIncreaseWeekday; }
    public void setAvgDailyIncreaseWeekday(Double v) { this.avgDailyIncreaseWeekday = v; }

    public Double getAvgDailyIncreaseWeekend() { return avgDailyIncreaseWeekend; }
    public void setAvgDailyIncreaseWeekend(Double v) { this.avgDailyIncreaseWeekend = v; }

    public LocalDateTime getLastUpdated() { return lastUpdated; }
}
