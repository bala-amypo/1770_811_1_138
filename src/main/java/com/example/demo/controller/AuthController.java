package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestService guestService;

    public AuthController(GuestService guestService) {
        this.guestService = guestService;
    }
    @PostMapping("/register")
    public ResponseEntity<Guest> register(@RequestBody RegisterRequest request) {

        Guest guest = new Guest();
        guest.setFullName(request.getFullName());
        guest.setEmail(request.getEmail());
        guest.setPassword(request.getPassword());
        guest.setPhoneNumber(request.getPhoneNumber());
        guest.setRole("ROLE_USER");

        Guest saved = guestService.createGuest(guest);
        return ResponseEntity.ok(saved);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        Guest guest = guestService.getGuestByEmail(request.getEmail());

        if (!guestService.matchesPassword(request.getPassword(), guest.getPassword())) {
            return ResponseEntity.badRequest().body("Invalid credentials");
        }

        return ResponseEntity.ok("Login successful");
    }
}
