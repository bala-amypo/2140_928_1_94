package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private UsagePatternModel modelUsed;

    private Double predictedLevel;
    private LocalDateTime predictedAt;

    private Integer daysUntilFull; // 🔴 added to match tests expecting Integer

    public OverflowPrediction() {
        this.predictedAt = LocalDateTime.now();
    }

    public OverflowPrediction(Bin bin, UsagePatternModel modelUsed, Double predictedLevel, Integer daysUntilFull) {
        this.bin = bin;
        this.modelUsed = modelUsed;
        this.predictedLevel = predictedLevel;
        this.daysUntilFull = daysUntilFull;
        this.predictedAt = LocalDateTime.now();
    }

    // ---------- getters & setters ----------
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }

    public Double getPredictedLevel() { return predictedLevel; }
    public void setPredictedLevel(Double predictedLevel) { this.predictedLevel = predictedLevel; }

    public LocalDateTime getPredictedAt() { return predictedAt; }
    public void setPredictedAt(LocalDateTime predictedAt) { this.predictedAt = predictedAt; }

    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }
}
