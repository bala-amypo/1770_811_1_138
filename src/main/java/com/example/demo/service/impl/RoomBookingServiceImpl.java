package com.example.demo.service.impl;

import com.example.demo.model.RoomBooking;
import com.example.demo.model.Guest;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.RoomBookingService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository bookingRepo;
    private final GuestRepository guestRepo;

    public RoomBookingServiceImpl(
            RoomBookingRepository bookingRepo,
            GuestRepository guestRepo
    ) {
        this.bookingRepo = bookingRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate())) {
            throw new IllegalArgumentException("Check-in must be before check-out");
        }

        if (booking.getGuest() == null || booking.getGuest().getId() == null) {
            throw new IllegalArgumentException("Guest is required");
        }

        Guest guest = guestRepo.findById(booking.getGuest().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        booking.setGuest(guest);

        return bookingRepo.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {
        RoomBooking existing = getBookingById(id);

        existing.setRoomNumber(booking.getRoomNumber());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());

        return bookingRepo.save(existing);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return bookingRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return bookingRepo.findByGuestId(guestId);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        bookingRepo.save(booking);
    }
}
