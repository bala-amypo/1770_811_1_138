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

    // Used by Spring
    public GuestServiceImpl(GuestRepository repo) {
        this.repo = repo;
        this.passwordEncoder = null;
    }

    // 🔥 Used by tests
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

    // ✅ REQUIRED BY INTERFACE
    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existing = getGuestById(id);

        existing.setFullName(guest.getFullName());
        existing.setEmail(guest.getEmail());
        existing.setPhoneNumber(guest.getPhoneNumber());
        existing.setRole(guest.getRole());
        existing.setVerified(guest.getVerified());

        if (guest.getPassword() != null && passwordEncoder != null) {
            existing.setPassword(passwordEncoder.encode(guest.getPassword()));
        }

        return repo.save(existing);
    }

    // ✅ REQUIRED BY INTERFACE
    @Override
    public void deactivateGuest(Long id) {
        Guest guest = getGuestById(id);
        guest.setActive(false);
        repo.save(guest);
    }
}
