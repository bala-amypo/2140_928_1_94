package com.example.demo.model;

public class Bin {
    private Long id;
    private String identifier;
    private Double capacityLiters;
    private Double latitude;
    private Double longitude;
    private Boolean active = true;
    private String locationDescription;
    private Zone zone;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getIdentifier() { return identifier; }
    public void setIdentifier(String identifier) { this.identifier = identifier; }

    public Double getCapacityLiters() { return capacityLiters; }
    public void setCapacityLiters(Double capacityLiters) { this.capacityLiters = capacityLiters; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getLocationDescription() { return locationDescription; }
    public void setLocationDescription(String locationDescription) { this.locationDescription = locationDescription; }

    public Zone getZone() { return zone; }
    public void setZone(Zone zone) { this.zone = zone; }
}
