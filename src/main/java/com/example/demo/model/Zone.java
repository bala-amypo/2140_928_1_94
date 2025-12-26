package com.example.demo.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zones")
public class Zone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String zoneName;
    
    private String description;
    private Boolean active = true;
    
    @OneToMany(mappedBy = "zone")
    private List<Bin> bins;
    
    // Getters and setters
}