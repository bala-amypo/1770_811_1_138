package com.example.demo.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

@Entity
@Table(name = "room_bookings")
public class RoomBooking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "guest_id")
    private Guest guest;

    private String roomNumber;

    private LocalDate checkInDate;
    private LocalDate checkOutDate;

    private Boolean active = true;
    @ManyToMany 
    @JoinTable(
    name = "roommates_booking",joinColumns=@JoinColumn(name="booking_id"),inverseJoinColumns=@JoinColumn(name="roommates_guest_id"))
    private Set<Guest> roommates = new HashSet<>();

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Guest getGuest() { return guest; }
    public void setGuest(Guest guest) { this.guest = guest; }

    public String getRoomNumber() { return roomNumber; }
    public void setRoomNumber(String roomNumber) { this.roomNumber = roomNumber; }

    public LocalDate getCheckInDate() { return checkInDate; }
    public void setCheckInDate(LocalDate checkInDate) { this.checkInDate = checkInDate; }

    public LocalDate getCheckOutDate() { return checkOutDate; }
    public void setCheckOutDate(LocalDate checkOutDate) { this.checkOutDate = checkOutDate; }

    public Boolean getActive() { return active; }
    public void setActive(Boolean active) { this.active = active; }

    public 
}
