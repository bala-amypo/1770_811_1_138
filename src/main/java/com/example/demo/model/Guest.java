package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class StudentEntity {
    @Id
    private Long id;
    private String fullName;
    @Column(unique=true)
    private String email;
    private boolean verified;
    private boolean active = true;
    
}