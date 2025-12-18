package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Zone() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {   // IMPORTANT: fixes your last error
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
