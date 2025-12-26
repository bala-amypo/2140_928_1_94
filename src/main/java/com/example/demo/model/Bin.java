package com.example.demo.model;

public class Bin {
    private Long id;
    private String locationDescription;
    private Integer capacityLiters; // wrapper for null checks
    private Boolean active;         // wrapper for null checks
    private Zone zone;

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public Integer getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Integer capacityLiters) { this.capacityLiters = capacityLiters; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}
