package com.example.demo.model;

import jakarta.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "bin_id")
    private Bin bin;
    
    private Date predictedFullDate;
    private Integer daysUntilFull;
    
    @ManyToOne
    @JoinColumn(name = "model_id")
    private UsagePatternModel modelUsed;
    
    private Timestamp generatedAt;
    
    public OverflowPrediction() {}
    
    public OverflowPrediction(Bin bin, Date predictedFullDate, Integer daysUntilFull, 
                             UsagePatternModel modelUsed, Timestamp generatedAt) {
        this.bin = bin;
        this.predictedFullDate = predictedFullDate;
        this.daysUntilFull = daysUntilFull;
        this.modelUsed = modelUsed;
        this.generatedAt = generatedAt;
    }
    
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
    public Date getPredictedFullDate() { return predictedFullDate; }
    public void setPredictedFullDate(Date predictedFullDate) { this.predictedFullDate = predictedFullDate; }
    public Integer getDaysUntilFull() { return daysUntilFull; }
    public void setDaysUntilFull(Integer daysUntilFull) { this.daysUntilFull = daysUntilFull; }
    public UsagePatternModel getModelUsed() { return modelUsed; }
    public void setModelUsed(UsagePatternModel modelUsed) { this.modelUsed = modelUsed; }
    public Timestamp getGeneratedAt() { return generatedAt; }
    public void setGeneratedAt(Timestamp generatedAt) { this.generatedAt = generatedAt; }
}