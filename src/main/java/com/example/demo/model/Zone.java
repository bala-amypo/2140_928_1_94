package com.example.demo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String zoneName;
    
    private String description;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @OneToMany(mappedBy = "zone", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Bin> bins = new ArrayList<>();
    
    // Constructors
    public Zone() {}
    
    public Zone(String zoneName) {
        this.zoneName = zoneName;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getZoneName() {
        return zoneName;
    }
    
    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public List<Bin> getBins() {
        return bins;
    }
    
    public void setBins(List<Bin> bins) {
        this.bins = bins;
    }
    
    // Helper methods
    public void addBin(Bin bin) {
        bins.add(bin);
        bin.setZone(this);
    }
    
    public void removeBin(Bin bin) {
        bins.remove(bin);
        bin.setZone(null);
    }
    
    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", zoneName='" + zoneName + '\'' +
                ", active=" + active +
                '}';
    }
}