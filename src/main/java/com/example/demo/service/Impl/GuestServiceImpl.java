package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Guest;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.GuestService;

@Service
public class GuestServiceImpl implements GuestService {

    @Autowired
    GuestRepository repo;

    @Override
    public Guest createGuest(Guest guest) {
        return repo.save(guest);
    }

    @Override
    public List<Guest> getGuests() {
        return repo.findAll();
    }

    @Override
    public Guest getGuestById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Guest updateGuest(Long id, Guest guest) {
        Guest existingGuest = repo.findById(id).orElse(null);

        if (existingGuest != null) {
            existingGuest.setFullName(guest.getFullName());
            existingGuest.setEmail(guest.getEmail());
            existingGuest.setPhoneNumber(guest.getPhoneNumber());
            existingGuest.setVerified(guest.isVerified());
            existingGuest.setRole(guest.getRole());
            return repo.save(existingGuest);
        }

        return null;
    }

    @Override
    public Guest deactivateGuest(Long id) {
        Guest guest = repo.findById(id).orElse(null);

        if (guest != null) {
            guest.setActive(false);
            return repo.save(guest);
        }

        return null;
    }
}
