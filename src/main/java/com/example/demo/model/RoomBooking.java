package com.example.demo.model;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Guest guest;

    private String roomNumber;

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private boolean active = true;

    @ManyToMany
    @JsonIgnore
    private List<Guest> roommates;

    public RoomBooking() {
    }

    public RoomBooking(
            LocalDate checkInDate,
            LocalDate checkOutDate,
            Guest guest,
            String roomNumber,
            List<Guest> roommates) {

        if (checkInDate.isAfter(checkOutDate)) {
            throw new IllegalArgumentException(
                "checkInDate must be before checkOutDate"
            );
        }

        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.guest = guest;
        this.roomNumber = roomNumber;
        this.roommates = roommates;
    }

    public Long getId() {
        return id;
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

    public List<Guest> getRoommates() {
        return roommates;
    }

    public void setRoommates(List<Guest> roommates) {
        this.roommates = roommates;
    }
}
