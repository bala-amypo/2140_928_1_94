package com.example.demo.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "overflow_predictions")
public class OverflowPrediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private Integer daysUntilFull;
    
    @Column(nullable = false)
    private LocalDate predictedFullDate;
    
    @Column(nullable = false)
    private LocalDateTime generatedAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false)
    private Bin bin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false)
    private UsagePatternModel modelUsed;
    
    // Constructors
    public OverflowPrediction() {
        this.generatedAt = LocalDateTime.now();
    }
    
    public OverflowPrediction(Integer daysUntilFull, Bin bin, UsagePatternModel modelUsed) {
        this.daysUntilFull = daysUntilFull;
        this.bin = bin;
        this.modelUsed = modelUsed;
        this.predictedFullDate = LocalDate.now().plusDays(daysUntilFull);
        this.generatedAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getDaysUntilFull() {
        return daysUntilFull;
    }
    
    public void setDaysUntilFull(Integer daysUntilFull) {
        this.daysUntilFull = daysUntilFull;
    }
    
    public LocalDate getPredictedFullDate() {
        return predictedFullDate;
    }
    
    public void setPredictedFullDate(LocalDate predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
    }
    
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    public Bin getBin() {
        return bin;
    }
    
    public void setBin(Bin bin) {
        this.bin = bin;
    }
    
    public UsagePatternModel getModelUsed() {
        return modelUsed;
    }
    
    public void setModelUsed(UsagePatternModel modelUsed) {
        this.modelUsed = modelUsed;
    }
    
    @Override
    public String toString() {
        return "OverflowPrediction{" +
                "id=" + id +
                ", daysUntilFull=" + daysUntilFull +
                ", predictedFullDate=" + predictedFullDate +
                ", generatedAt=" + generatedAt +
                '}';
    }
}