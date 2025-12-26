package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.DigitalKey;
import com.example.demo.model.Guest;
import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository keyShareRequestRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    public KeyShareRequestServiceImpl(
            KeyShareRequestRepository keyShareRequestRepository,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository) {

        this.keyShareRequestRepository = keyShareRequestRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {

        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("Share end must be after share start");
        }

        if (request.getSharedBy().getId()
                .equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith cannot be same");
        }

        DigitalKey key = digitalKeyRepository.findById(
                request.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found"));

        Guest sharedBy = guestRepository.findById(
                request.getSharedBy().getId())
                .orElseThrow(() -> new ResourceNotFoundException("SharedBy not found"));

        Guest sharedWith = guestRepository.findById(
                request.getSharedWith().getId())
                .orElseThrow(() -> new ResourceNotFoundException("SharedWith not found"));

        request.setDigitalKey(key);
        request.setSharedBy(sharedBy);
        request.setSharedWith(sharedWith);
        request.setStatus("APPROVED");

        return keyShareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest updateStatus(Long id, String status) {

        KeyShareRequest request = keyShareRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "KeyShareRequest not found with id " + id));

        request.setStatus(status);
        return keyShareRequestRepository.save(request);
    }

    @Override
    public KeyShareRequest getShareRequestById(Long id) {
        return keyShareRequestRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "KeyShareRequest not found with id " + id));
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedBy(Long guestId) {
        return keyShareRequestRepository.findBySharedById(guestId);
    }

    @Override
    public List<KeyShareRequest> getRequestsSharedWith(Long guestId) {
        return keyShareRequestRepository.findBySharedWithId(guestId);
    }
}
