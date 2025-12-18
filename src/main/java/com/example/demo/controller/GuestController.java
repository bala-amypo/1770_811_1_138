package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Guest;
import com.example.demo.service.GuestService;


@RestController
public class GuestController {

    @Autowired
    GuestService ser;

    @PostMapping("/api/guests")
    public Guest createguest(@RequestBody Guest guest){
        return ser.createguest(guest);
    }
    
    @GetMapping("/api/guests")
    public List<Guest> getguests() {
        return ser.getguests();
    }
    
    @GetMapping("/api/guests/{id}")
    public Guest getGuestById(@PathVariable Long id) {
        return ser.getGuestById(id);
    }

    @PutMapping("/api/guests/{id}")
    public Guest updateguest(@PathVariable Long id){
        return ser.updateguest(id);
    }

    
}