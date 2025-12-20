package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class OverflowPrediction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int predictedFillLevel;

    private LocalDateTime predictedAt;

    @ManyToOne
    private Bin bin;

    public Long getId() {
        return id;
    }

    public int getPredictedFillLevel() {
        return predictedFillLevel;
    }

    public void setPredictedFillLevel(int predictedFillLevel) {
        this.predictedFillLevel = predictedFillLevel;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }
}
