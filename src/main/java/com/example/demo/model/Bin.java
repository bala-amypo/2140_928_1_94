package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "bins")
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String identifier;
    private Double capacityLiters;
    private Double latitude;
    private Double longitude;
    private String locationDescription;
    private Boolean active = true;
    
    @ManyToOne
    @JoinColumn(name = "zone_id")
    private Zone zone;
    
    // Getters and setters
}