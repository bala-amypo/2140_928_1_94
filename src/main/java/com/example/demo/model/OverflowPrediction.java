package com.example.demo.model;

import java.time.LocalDateTime;

public class OverflowPrediction {

    private Bin bin;
    private boolean willOverflow;
    private LocalDateTime predictedAt;

    public Bin getBin() {
        return bin;
    }

    public void setBin(Bin bin) {
        this.bin = bin;
    }

    public boolean isWillOverflow() {
        return willOverflow;
    }

    public void setWillOverflow(boolean willOverflow) {
        this.willOverflow = willOverflow;
    }

    public LocalDateTime getPredictedAt() {
        return predictedAt;
    }

    public void setPredictedAt(LocalDateTime predictedAt) {
        this.predictedAt = predictedAt;
    }
}
