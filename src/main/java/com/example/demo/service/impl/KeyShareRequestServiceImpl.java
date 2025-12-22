package com.example.demo.service.impl;

import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;
    private final DigitalKeyRepository digitalKeyRepo;
    private final GuestRepository guestRepo;

    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository repo,
            DigitalKeyRepository digitalKeyRepo,
            GuestRepository guestRepo
    ) {
        this.repo = repo;
        this.digitalKeyRepo = digitalKeyRepo;
        this.guestRepo = guestRepo;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {

        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after start");
        }

        if (request.getSharedBy().getId().equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("Cannot share key with same guest");
        }
        DigitalKey key = digitalKeyRepo.findById(request.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Digital key not found"));

        Guest sharedBy = guestRepo.findById(request.getSharedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("SharedBy guest not found"));

        Guest sharedWith = guestRepo.findById(request.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("SharedWith guest not found"));
        request.setDigitalKey(key);
        request.setSharedBy(sharedBy);
        request.setSharedWith(sharedWith);
        request.setStatus("PENDING");

        return repo.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {
        KeyShareRequest req = getShareRequestById(id);
        req.setStatus(status);
        return repo.save(req);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Request not found"));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return repo.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return repo.findBySharedWithId(guestId);
    }
}
