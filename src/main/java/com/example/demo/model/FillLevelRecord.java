package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class FillLevelRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Bin bin;

    @Column(nullable = false)
    private int fillPercentage;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    private boolean isWeekend;

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }

    public int getFillPercentage() { return fillPercentage; }
    public void setFillPercentage(int fillPercentage) { this.fillPercentage = fillPercentage; }

    public LocalDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }

    public boolean isWeekend() { return isWeekend; }
    public void setWeekend(boolean weekend) { isWeekend = weekend; }
}
