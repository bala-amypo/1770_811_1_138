package com.example.demo.model;

import java.time.LocalDate;

import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Entity;
import java.util.*;
@Entity
public class RoomBooking {
    @Id
    private Long id;
    @ManyToOne
    private Guest guest;
    private String roomNumber;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private boolean  active = true;
    @ManyToMany
    private List<Guest> roommates;

    public RoomBooking(LocalDate checkInDate, LocalDate checkOutDate, Guest guest, Long id, String roomNumber, Guest roommates) {
        if(checkInDate.isAfter(checkOutDate)){
            throw new IllegalArgumentException("checkIndate must be before checkout date");
        }
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guest = guest;
        this.id = id;
        this.roomNumber = roomNumber;
        this.roommates = roommates;
    }

    public RoomBooking() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Guest getGuest() {
        return guest;
    }

    public void setGuest(Guest guest) {
        this.guest = guest;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Guest getRoommates() {
        return roommates;
    }

    public void setRoommates(Guest roommates) {
        this.roommates = roommates;
    }

}

