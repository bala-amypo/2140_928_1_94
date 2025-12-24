package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 🔴 FIX: LAZY → EAGER
    @NotNull(message = "Bin must not be null")
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @NotNull(message = "Average daily usage is required")
    @Min(value = 0, message = "Average daily usage must be >= 0")
    @Column(name = "average_daily_usage", nullable = false)
    private Double averageDailyUsage;

    @NotNull(message = "Peak usage is required")
    @Min(value = 0, message = "Peak usage must be >= 0")
    @Column(name = "peak_usage", nullable = false)
    private Double peakUsage;

    @NotNull(message = "Pattern type is required")
    @Column(name = "pattern_type", nullable = false, length = 20)
    private String patternType;

    // ===== GETTERS & SETTERS =====

    public Long getId() {
        return id;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public Double getAverageDailyUsage() {
        return averageDailyUsage;
    }

    public void setAverageDailyUsage(Double averageDailyUsage) {
        this.averageDailyUsage = averageDailyUsage;
    }

    public Double getPeakUsage() {
        return peakUsage;
    }

    public void setPeakUsage(Double peakUsage) {
        this.peakUsage = peakUsage;
    }

    public String getPatternType() {
        return patternType;
    }

    public void setPatternType(String patternType) {
        this.patternType = patternType;
    }
}
