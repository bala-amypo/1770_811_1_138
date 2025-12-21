package com.example.HotelRoomKeyDigitalShare.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.HotelRoomKeyDigitalShare.model.RoomBooking;
import com.example.HotelRoomKeyDigitalShare.service.RoomBookingService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

import java.util.List;
@RestController
public class RoomBookingController {
    @Autowired
    RoomBookingService ser;

    @PostMapping("/api/bookings")
    public RoomBooking createBooking(@RequestBody RoomBooking roombooking){
        return ser.createBooking(roombooking);
    }

    @PutMapping("/api/bookings/{id}")
    public RoomBooking updateBooking(@PathVariable Long id,@RequestBody RoomBooking roombooking){
        return ser.updateBooking(id,roombooking);
    }
    @GetMapping("/api/bookings/{id}")
    public RoomBooking getBookingById(@PathVariable Long id) {
        return ser.getBookingById(id);
    }
    @GetMapping("/api/bookings/guests/{guestId}")
    public List<RoomBooking> getBookingsByGuest(@PathVariable Long guestid){
        return ser.getBookingsByGuest(guestid);
    }
    @PutMapping("/api/bookings/{id}/deactivate")
    public String deactivateBooking(@PathVariable Long id){
        RoomBooking booking = ser.getBookingById(id);
        if(booking!=null){
            ser.deactivateBooking(id);
            return "Booking deactivated successfully";
        }else{
            return "Booking not found";
        }
    }
}

