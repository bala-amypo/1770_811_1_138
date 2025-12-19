package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.PrePersist;

@Entity
public class Guest {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    @Column(unique=true)
    private String email;
    private String phoneNumber;
    private boolean verified;
    private boolean active = true;
    private String role;
    private LocalDateTime createdAt;
    @PrePersist
    public void setLocalDateTime(){
        this.createdAt = LocalDateTime.now();
    }

    public Guest(String fullName,String email,String phoneNumber,boolean verified,boolean active,String role){
    this.fullName=fullName;
    this.email=email;
    this.phoneNumber=phoneNumber;
    this.verified=verified;
    this.active=active;
    this.role=role;
    }
    public Guest(){
    }

    public void setEmail(String email){
        this.email=email;
    }
    public void setFullName(String fullName){
        this.fullName=fullName;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber=phoneNumber;
    }
    public void setVerified(boolean verified){
        this.verified=verified;
    }
    public void setActive(boolean active){
        this.active=active;
    }
    public void setRole(String role){
        this.role=role;
    }
    public void setCreatedAt(LocalDateTime createdAt){
        this.createdAt=createdAt;
    }
    public String getFullName(){
        return this.fullName;
    }
    public String getEmail(){
        return this.email;
    }
    public String getPhoneNumber(){
        return this.phoneNumber;
    }
    public boolean getVerified(){
        return this.verified;
    }
    public boolean getActive(){
        return this.active;
    }
    public String getRole(){
        return this.role;
    }
    public LocalDateTime getCreatedAt(){
        return this.createdAt;
    }
    public boolean isVerified() {
    return verified;
    }

}