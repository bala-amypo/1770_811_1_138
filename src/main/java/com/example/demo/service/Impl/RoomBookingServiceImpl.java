package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.RoomBooking;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.RoomBookingService;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {
    @Autowired
    RoomBookingRepository repo;

    @Override
    public RoomBooking createBooking(RoomBooking roombooking) {
        return repo.save(roombooking);
    }

    @Override
    public RoomBooking updateBooking(Long id, RoomBooking roombooking) {
        RoomBooking existingBooking = repo.findById(id).orElse(null);
        if (existingBooking != null) {
            existingBooking.setGuest(roombooking.getGuest());
            existingBooking.setRoomNumber(roombooking.getRoomNumber());
            existingBooking.setCheckInDate(roombooking.getCheckInDate());
            existingBooking.setCheckOutDate(roombooking.getCheckOutDate());
            existingBooking.setRoommates(roombooking.getRoommates());
            return repo.save(existingBooking);
        }

        return null;
    }

    @Override
    public RoomBooking getBookingById(Long id) {
       return repo.findById(id).orElse(null);
    }

    @Override
    public List<RoomBooking> getBookingsByGuest(Long guestid) {
        return repo.findByGuestId(guestid);
    }

    @Override
    public RoomBooking deactivateBooking(Long id) {
        RoomBooking booking = repo.findById(id).orElse(null);
        if(booking!=null){
            booking.setActive(false);
            return repo.save(booking);
        }
        return null;
    }
}
