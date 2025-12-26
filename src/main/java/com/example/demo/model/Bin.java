package com.example.demo.model;

public class Bin {

    private Long id;
    private int capacityLiters; // capacity in liters
    private Zone zone;
    private boolean active;
    private String locationDescription;

    // ID
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    // Capacity
    public int getCapacityLiters() {
        return capacityLiters;
    }

    public void setCapacityLiters(int capacityLiters) {
        this.capacityLiters = capacityLiters;
    }

    // Zone
    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    // Active
    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    // Location Description
    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }
}
