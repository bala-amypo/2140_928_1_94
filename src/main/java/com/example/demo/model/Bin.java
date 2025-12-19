package com.example.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Identifier is required")
    @Column(unique = true, nullable = false)
    private String identifier;

    private String locationDescription;
    private Double latitude;
    private Double longitude;

    @NotNull(message = "Zone is required")
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;

    @Min(value = 1, message = "Bin capacity must be greater than 0")
    private int capacityLiters;

    private boolean active = true;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }

    public int getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(int capacityLiters) { this.capacityLiters = capacityLiters; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }
}
