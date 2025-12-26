package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bins")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String identifier;
    
    private Double capacityLiters;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    
    @Column(nullable = false)
    private Boolean active = true;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    // Constructors
    public Bin() {}
    
    public Bin(String identifier, Double capacityLiters, Zone zone) {
        this.identifier = identifier;
        this.capacityLiters = capacityLiters;
        this.zone = zone;
        this.active = true;
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getIdentifier() {
        return identifier;
    }
    
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    
    public Double getCapacityLiters() {
        return capacityLiters;
    }
    
    public void setCapacityLiters(Double capacityLiters) {
        this.capacityLiters = capacityLiters;
    }
    
    public Double getLatitude() {
        return latitude;
    }
    
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
    
    public Double getLongitude() {
        return longitude;
    }
    
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
    
    public String getLocationDescription() {
        return locationDescription;
    }
    
    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
    
    public Boolean getActive() {
        return active;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }
    
    public Zone getZone() {
        return zone;
    }
    
    public void setZone(Zone zone) {
        this.zone = zone;
    }
    
    @Override
    public String toString() {
        return "Bin{" +
                "id=" + id +
                ", identifier='" + identifier + '\'' +
                ", active=" + active +
                '}';
    }
}