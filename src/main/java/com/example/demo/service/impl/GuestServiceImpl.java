package com.example.demo.service.impl;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository repo;

    public GuestServiceImpl(GuestRepository repo) {
        this.repo = repo;
    }

    @Override
    public Guest createGuest(Guest guest) {
        repo.findByEmail(guest.getEmail()).ifPresent(g -> {
            throw new IllegalArgumentException("Email already exists");
        });
        return repo.save(guest);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest g = getGuestById(id);
        g.setFullName(guest.getFullName());
        g.setPhoneNumber(guest.getPhoneNumber());
        g.setRole(guest.getRole());
        return repo.save(g);
    }

    @Override
    public Guest getGuestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));
    }

    @Override
    public List<Guest> getAllGuests() {
        return repo.findAll();
    }

    @Override
    public void deactivateGuest(Long id) {
        Guest g = getGuestById(id);
        g.setActive(false);
        repo.save(g);
    }
}
