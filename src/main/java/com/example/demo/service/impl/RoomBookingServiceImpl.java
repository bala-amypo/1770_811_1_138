package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Guest;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

    private final RoomBookingRepository bookingRepository;
    private final GuestRepository guestRepository;

    public RoomBookingServiceImpl(RoomBookingRepository bookingRepository,
                                  GuestRepository guestRepository) {
        this.bookingRepository = bookingRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public RoomBooking createBooking(RoomBooking booking) {

        if (booking.getCheckInDate().isAfter(booking.getCheckOutDate())) {
            throw new IllegalArgumentException(
                    "Check-in date must be before check-out date");
        }

        Long guestId = booking.getGuest().getId();
        Guest guest = guestRepository.findById(guestId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Guest not found: " + guestId));

        booking.setGuest(guest);

        if (booking.getRoommates() != null && !booking.getRoommates().isEmpty()) {
            Set<Guest> resolvedRoommates = new HashSet<>();

            for (Guest g : booking.getRoommates()) {
                Guest roommate = guestRepository.findById(g.getId())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Roommate not found: " + g.getId()));
                resolvedRoommates.add(roommate);
            }

            booking.setRoommates(resolvedRoommates);
        }

        return bookingRepository.save(booking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking booking) {

        RoomBooking existing = bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found: " + id));

        existing.setRoomNumber(booking.getRoomNumber());
        existing.setCheckInDate(booking.getCheckInDate());
        existing.setCheckOutDate(booking.getCheckOutDate());

        return bookingRepository.save(existing);
    }

    @Override
    public RoomBooking getBookingById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Booking not found: " + id));
    }

    @Override
    public List<RoomBooking> getBookingsForGuest(Long guestId) {
        return bookingRepository.findByGuestId(guestId);
    }

    @Override
    public void deactivateBooking(Long id) {
        RoomBooking booking = getBookingById(id);
        booking.setActive(false);
        bookingRepository.save(booking);
    }
}
