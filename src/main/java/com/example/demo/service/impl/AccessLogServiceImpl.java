package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.repository.DigitalKeyRepository;
import com.example.demo.repository.GuestRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository repo;

    // REQUIRED BY TESTS (even if unused)
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;

    public AccessLogServiceImpl(
            AccessLogRepository repo,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository
    ) {
        this.repo = repo;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
    }
    // REQUIRED BY TESTS
public AccessLogServiceImpl(
        AccessLogRepository repo,
        DigitalKeyRepository digitalKeyRepository,
        GuestRepository guestRepository,
        KeyShareRequestRepository keyShareRequestRepository
) {
    this.repo = repo;
    this.digitalKeyRepository = digitalKeyRepository;
    this.guestRepository = guestRepository;
}


    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAccessTime().isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("future");
        }

        log.setResult("SUCCESS");
        return repo.save(log);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return repo.findByDigitalKeyId(keyId);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return repo.findByGuestId(guestId);
    }
}
