package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.RoomBooking;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.RoomBookingRepository;
import com.example.demo.service.DigitalKeyService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class DigitalKeyServiceImpl implements DigitalKeyService {

    private final DigitalKeyRepository keyRepo;
    private final RoomBookingRepository bookingRepo;

    public DigitalKeyServiceImpl(DigitalKeyRepository keyRepo, RoomBookingRepository bookingRepo) {
        this.keyRepo = keyRepo;
        this.bookingRepo = bookingRepo;
    }

    @Override
    public DigitalKey generateKey(Long bookingId) {
        RoomBooking booking = bookingRepo.findById(bookingId).orElseThrow();
        DigitalKey key = new DigitalKey();
        key.setBooking(booking);
        key.setKeyValue(UUID.randomUUID().toString());
        key.setIssuedAt(LocalDateTime.now());
        key.setExpiresAt(LocalDateTime.now().plusDays(1));
        return keyRepo.save(key);
    }
}
