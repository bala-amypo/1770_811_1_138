package com.example.demo.service;

import java.util.List;

import com.example.demo.model.Guest;

public interface GuestService {

    public Guest createGuest(Guest guest);

    public List<Guest> getAllGuests();

    public Guest getGuestById(Long id);

    public Guest updateGuest(Long id, Guest guest);

    public Guest deactivateGuest(Long id);
}
