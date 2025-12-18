package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.time.LocalDateTime;
@Entity
public class StudentEntity {
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
    public void setActuiv(String email){
        this.email=email;
    }
    public void setEmail(String email){
        this.email=email;
    }
}