package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Overflow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean overflowed;

    public Long getId() { return id; }
    public boolean isOverflowed() { return overflowed; }
    public void setOverflowed(boolean overflowed) { this.overflowed = overflowed; }
}
