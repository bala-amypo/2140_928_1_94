package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Bin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String identifier;

    private String locationDescription;
    private double latitude;
    private double longitude;

    @ManyToOne
    private Zone zone;

    private double capacityLiters;
    private boolean active = true;

    // getters and setters
}
