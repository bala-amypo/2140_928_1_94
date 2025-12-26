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
    
    @Column(nullable = false)
    private Double confidenceScore;
    
    @Column(length = 500)
    private String notes;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bin_id", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_overflow_prediction_bin"))
    private Bin bin;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", nullable = false, 
                foreignKey = @ForeignKey(name = "FK_overflow_prediction_model"))
    private UsagePatternModel modelUsed;
    
    // Constructors
    public OverflowPrediction() {
        this.generatedAt = LocalDateTime.now();
        this.confidenceScore = 0.0;
    }
    
    public OverflowPrediction(Integer daysUntilFull, Bin bin, UsagePatternModel modelUsed) {
        this();
        this.daysUntilFull = daysUntilFull;
        this.bin = bin;
        this.modelUsed = modelUsed;
        this.predictedFullDate = LocalDate.now().plusDays(daysUntilFull);
    }
    
    public OverflowPrediction(Integer daysUntilFull, Bin bin, UsagePatternModel modelUsed, 
                             Double confidenceScore, String notes) {
        this(daysUntilFull, bin, modelUsed);
        this.confidenceScore = confidenceScore;
        this.notes = notes;
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
        if (daysUntilFull != null) {
            this.predictedFullDate = LocalDate.now().plusDays(daysUntilFull);
        }
    }
    
    public LocalDate getPredictedFullDate() {
        return predictedFullDate;
    }
    
    public void setPredictedFullDate(LocalDate predictedFullDate) {
        this.predictedFullDate = predictedFullDate;
        if (predictedFullDate != null) {
            LocalDate today = LocalDate.now();
            this.daysUntilFull = (int) java.time.temporal.ChronoUnit.DAYS.between(today, predictedFullDate);
        }
    }
    
    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }
    
    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }
    
    public Double getConfidenceScore() {
        return confidenceScore;
    }
    
    public void setConfidenceScore(Double confidenceScore) {
        this.confidenceScore = confidenceScore;
    }
    
    public String getNotes() {
        return notes;
    }
    
    public void setNotes(String notes) {
        this.notes = notes;
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
    
    // Helper methods
    public boolean isExpired() {
        return generatedAt.isBefore(LocalDateTime.now().minusHours(24));
    }
    
    public boolean isUrgent() {
        return daysUntilFull != null && daysUntilFull <= 1;
    }
    
    public String getStatus() {
        if (daysUntilFull == null) return "UNKNOWN";
        if (daysUntilFull <= 0) return "OVERFLOW_IMMINENT";
        if (daysUntilFull <= 1) return "URGENT";
        if (daysUntilFull <= 3) return "WARNING";
        return "NORMAL";
    }
    
    @Override
    public String toString() {
        return "OverflowPrediction{" +
                "id=" + id +
                ", daysUntilFull=" + daysUntilFull +
                ", predictedFullDate=" + predictedFullDate +
                ", generatedAt=" + generatedAt +
                ", confidenceScore=" + confidenceScore +
                ", status=" + getStatus() +
                '}';
    }
}

