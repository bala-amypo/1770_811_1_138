package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "digital_keys")
public class DigitalKey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private RoomBooking booking;

    @Column(unique = true)
    private String keyValue;

    private LocalDateTime issuedAt;
    private LocalDateTime expiresAt;

    private Boolean active = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public RoomBooking getBooking() { return booking; }
    public void setBooking(RoomBooking booking) { this.booking = booking; }

    public String getKeyValue() { return keyValue; }
    public void setKeyValue(String keyValue) { this.keyValue = keyValue; }

    public LocalDateTime getIssuedAt() { return issuedAt; }

    // ✅ APP USE
    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }

    // 🔥 TEST USE (Instant → LocalDateTime)
    public void setIssuedAt(Instant issuedAt) {
        this.issuedAt = LocalDateTime.ofInstant(issuedAt, ZoneId.systemDefault());
    }

    public LocalDateTime getExpiresAt() { return expiresAt; }

    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }

    // 🔥 TEST USE
    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = LocalDateTime.ofInstant(expiresAt, ZoneId.systemDefault());
    }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }
}
