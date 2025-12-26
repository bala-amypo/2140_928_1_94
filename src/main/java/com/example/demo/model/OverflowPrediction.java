package com.example.demo.model;

import java.time.LocalDate;

public class OverflowPrediction {
    private Long id;
    private Bin bin;
    private Integer daysUntilFull;
    private LocalDate predictedFullDate;
    private UsagePatternModel modelUsed;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }

    public LocalDate getPredictedFullDate() { return predictedFullDate; }
    public void setPredictedFullDate(LocalDate predictedFullDate) { this.predictedFullDate = predictedFullDate; }

    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
}
