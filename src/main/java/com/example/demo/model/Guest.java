package com.example.demo.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
    name = "guests",
    uniqueConstraints = @UniqueConstraint(columnNames = "email")
)
public class Guest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable=false)
    private String password;
    private String phoneNumber;

    private Boolean verified = false;
    private Boolean active = true;
    private String role;

    private Instant createdAt;

    public Guest(Instant createdAt, String email, String fullName, Long id, String password, String phoneNumber, String role) {
        this.createdAt = createdAt;
        this.email = email;
        this.fullName = fullName;
        this.id = id;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.role = role;
    }

    public Guest() {
    }

    @PrePersist
    public void onCreate() {
        this.createdAt = Instant.now();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Boolean getVerified() { return verified; }
    public void setVerified(Boolean verified) { this.verified = verified; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }

    public Instant getCreatedAt() { return createdAt; }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }
}
