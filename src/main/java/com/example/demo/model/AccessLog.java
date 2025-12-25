package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "access_logs")
public class AccessLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DigitalKey digitalKey;

    @ManyToOne
    private Guest guest;

    private LocalDateTime accessTime;

    private String result;
    private String reason;

    public Long getId() { return id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public LocalDateTime getAccessTime() { return accessTime; }

    // ✅ APP
    public void setAccessTime(LocalDateTime accessTime) {
        this.accessTime = accessTime;
    }

    // 🔥 TEST
    public void setAccessTime(Instant accessTime) {
        this.accessTime = LocalDateTime.ofInstant(accessTime, ZoneId.systemDefault());
    }

    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
}
