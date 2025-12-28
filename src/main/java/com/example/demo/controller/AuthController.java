package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.model.Guest;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.GuestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final GuestService guestService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthController(
            GuestService guestService,
            AuthenticationManager authenticationManager,
            JwtTokenProvider jwtTokenProvider) {

        this.guestService = guestService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
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

        // 🔐 Authenticate using Spring Security
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        // 🔑 Generate token using Authentication object
        String token = jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(token);
    }
}
