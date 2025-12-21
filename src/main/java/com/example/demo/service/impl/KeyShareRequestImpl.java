package com.example.demo.service.impl;

import com.example.demo.model.KeyShareRequest;
import com.example.demo.repository.KeyShareRequestRepository;
import com.example.demo.service.KeyShareRequestService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyShareRequestServiceImpl implements KeyShareRequestService {

    private final KeyShareRequestRepository repo;

    public KeyShareRequestServiceImpl(KeyShareRequestRepository repo) {
        this.repo = repo;
    }

    @Override
    public KeyShareRequest createShareRequest(KeyShareRequest request) {
        if (request.getShareEnd().isBefore(request.getShareStart())) {
            throw new IllegalArgumentException("share end");
        }
        if (request.getSharedBy().getId().equals(request.getSharedWith().getId())) {
            throw new IllegalArgumentException("sharedBy and sharedWith");
        }
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
