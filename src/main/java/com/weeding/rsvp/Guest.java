package com.weeding.rsvp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private boolean willAttend;

    private String allergies;

    private String specialRequests;

    private boolean plusOne;

    // Default constructor
    public Guest() {}

    // Constructor with fields
    public Guest(String name, boolean willAttend, String allergies, String specialRequests, boolean plusOne) {
        this.name = name;
        this.willAttend = willAttend;
        this.allergies = allergies;
        this.specialRequests = specialRequests;
        this.plusOne = plusOne;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isWillAttend() {
        return willAttend;
    }

    public void setWillAttend(boolean willAttend) {
        this.willAttend = willAttend;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public String getSpecialRequests() {
        return specialRequests;
    }

    public void setSpecialRequests(String specialRequests) {
        this.specialRequests = specialRequests;
    }

    public boolean isPlusOne() {
        return plusOne;
    }

    public void setPlusOne(boolean plusOne) {
        this.plusOne = plusOne;
    }
}
