package com.example.demo.model;

import jakarta.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name = "key_share_requests")
public class KeyShareRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private DigitalKey digitalKey;

    @ManyToOne
    private Guest sharedBy;

    @ManyToOne
    private Guest sharedWith;

    private LocalDateTime shareStart;
    private LocalDateTime shareEnd;

    private String status;
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    public LocalDateTime getShareStart() { return shareStart; }

    public void setShareStart(LocalDateTime shareStart) {
        this.shareStart = shareStart;
    }

    // 🔥 TEST
    public void setShareStart(Instant shareStart) {
        this.shareStart = LocalDateTime.ofInstant(shareStart, ZoneId.systemDefault());
    }

    public LocalDateTime getShareEnd() { return shareEnd; }

    public void setShareEnd(LocalDateTime shareEnd) {
        this.shareEnd = shareEnd;
    }

    // 🔥 TEST
    public void setShareEnd(Instant shareEnd) {
        this.shareEnd = LocalDateTime.ofInstant(shareEnd, ZoneId.systemDefault());
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
