package com.example.demo.service.impl;

import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;
import com.example.demo.exception.ResourceNotFoundException;

import java.util.List;

public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository repo;

    public RoomBookingServiceImpl(RoomBookingRepository repo) {
        this.repo = repo;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {
        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in must be before check-out");
        }
        return repo.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {
        RoomBooking b = getBookingById(id);
        b.setRoomNumber(booking.getRoomNumber());
        b.setCheckInDate(booking.getCheckInDate());
        b.setCheckOutDate(booking.getCheckOutDate());
        return repo.save(b);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking b = getBookingById(id);
        b.setActive(false);
        repo.save(b);
    }
}
