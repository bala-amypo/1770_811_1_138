package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    private Instant shareStart;
    private Instant shareEnd;

    private String status;

    public KeyShareRequest(DigitalKey digitalKey, Long id, Instant shareEnd, Instant shareStart, Guest sharedBy, Guest sharedWith, String status) {
        this.digitalKey = digitalKey;
        this.id = id;
        this.shareEnd = shareEnd;
        this.shareStart = shareStart;
        this.sharedBy = sharedBy;
        this.sharedWith = sharedWith;
        this.status = status;
    }

    public KeyShareRequest() {
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public DigitalKey getDigitalKey() { return digitalKey; }
    public void setDigitalKey(DigitalKey digitalKey) { this.digitalKey = digitalKey; }

    public Guest getSharedBy() { return sharedBy; }
    public void setSharedBy(Guest sharedBy) { this.sharedBy = sharedBy; }

    public Guest getSharedWith() { return sharedWith; }
    public void setSharedWith(Guest sharedWith) { this.sharedWith = sharedWith; }

    public Instant getShareStart() { return shareStart; }
    public void setShareStart(Instant shareStart) { this.shareStart = shareStart; }

    public Instant getShareEnd() { return shareEnd; }
    public void setShareEnd(Instant shareEnd) { this.shareEnd = shareEnd; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
