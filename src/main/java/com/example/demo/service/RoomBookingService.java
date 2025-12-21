package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoomBooking;

public interface RoomBookingService {

    RoomBooking createBooking(RoomBooking roombooking);

    RoomBooking updateBooking(Long id, RoomBooking roombooking);

    RoomBooking getBookingById(Long id);

    List<RoomBooking> getBookingsByGuests(Long guestid);

    void deactivateBooking(Long id);
}
