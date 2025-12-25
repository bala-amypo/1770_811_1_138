package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository repo;
    private final PasswordEncoder passwordEncoder;

    // ORIGINAL CONSTRUCTOR (used by Spring)
    public GuestServiceImpl(GuestRepository repo) {
        this.repo = repo;
        this.passwordEncoder = null;
    }

    // 🔥 REQUIRED FOR TESTS
    public GuestServiceImpl(GuestRepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Guest createGuest(Guest guest) {
        if (passwordEncoder != null && guest.getPassword() != null) {
            guest.setPassword(passwordEncoder.encode(guest.getPassword()));
        }
        guest.setCreatedAt(Instant.now());
        return repo.save(guest);
    }

    @Override
    public Guest getGuestById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public List<Guest> getAllGuests() {
        return repo.findAll();
    }
}
