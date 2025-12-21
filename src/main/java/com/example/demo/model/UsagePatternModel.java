package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name = "usage_pattern_models")
public class UsagePatternModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelData;

    @ManyToOne(optional = false)
    @JoinColumn(name = "bin_id")
    private Bin bin;

    public Long getId() { return id; }

    public String getModelData() { return modelData; }
    public void setModelData(String modelData) { this.modelData = modelData; }

    public Bin getBin() { return bin; }
    public void setBin(Bin bin) { this.bin = bin; }
}
