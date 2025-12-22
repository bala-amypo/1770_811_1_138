package com.example.demo.controller;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/api/key-share")
public class KeyShareRequestController {

     @Autowired
     KeyShareRequestService service;

    @PostMapping
    public KeyShareRequest create(@RequestBody KeyShareRequest request) {
        return service.createShareRequest(request);
    }

    @PutMapping("/{id}/status")
    public KeyShareRequest update(@PathVariable Long id, @RequestParam String status) {
        return service.updateStatus(id, status);
    }

    @GetMapping("/{id}")
    public KeyShareRequest get(@PathVariable Long id) {
        return service.getShareRequestById(id);
    }

    @GetMapping("/shared-by/{guestId}")
    public List<KeyShareRequest> sharedBy(@PathVariable Long guestId) {
        return service.getRequestsSharedBy(guestId);
    }

    @GetMapping("/shared-with/{guestId}")
    public List<KeyShareRequest> sharedWith(@PathVariable Long guestId) {
        return service.getRequestsSharedWith(guestId);
    }
}
