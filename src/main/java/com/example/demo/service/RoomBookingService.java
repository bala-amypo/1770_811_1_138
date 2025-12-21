package com.example.demo.service;

import java.util.List;

import com.example.demo.model.RoomBooking;

public interface RoomBookingService {

    RoomBooking createBooking(RoomBooking roombooking);

    RoomBooking updateBooking(Long id, RoomBooking roombooking);

    RoomBooking getBookingById(Long id);

    List<RoomBooking> getBookingsByGuest(Long guestid);

    RoomBooking deactivateBooking(Long id);
}
