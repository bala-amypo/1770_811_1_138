package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RoomBooking booking;

    private String keyValue;
    private Instant issuedAt;
    private Instant expiresAt;
    private Boolean active = true;

    public DigitalKey(RoomBooking booking, Instant expiresAt, Long id, Instant issuedAt, String keyValue) {
        this.booking = booking;
        this.expiresAt = expiresAt;
        this.id = id;
        this.issuedAt = issuedAt;
        this.keyValue = keyValue;
    }

    public DigitalKey() {
    }

    public Long getId() { return id; }
    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }
    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }
    public Instant getIssuedAt() { return issuedAt; }
    public void setIssuedAt(Instant issuedAt) { this.issuedAt = issuedAt; }
    public Instant getExpiresAt() { return expiresAt; }
    public void setExpiresAt(Instant expiresAt) { this.expiresAt = expiresAt; }
    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public void setId(Long id) {
        this.id = id;
    }
}
