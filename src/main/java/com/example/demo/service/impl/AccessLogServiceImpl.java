package com.example.demo.service.impl;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.AccessLogService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    private final AccessLogRepository accessLogRepository;
    private final DigitalKeyRepository digitalKeyRepository;
    private final GuestRepository guestRepository;
    private final KeyShareRequestRepository keyShareRequestRepository;

    public AccessLogServiceImpl(
            AccessLogRepository accessLogRepository,
            DigitalKeyRepository digitalKeyRepository,
            GuestRepository guestRepository,
            KeyShareRequestRepository keyShareRequestRepository) {

        this.accessLogRepository = accessLogRepository;
        this.digitalKeyRepository = digitalKeyRepository;
        this.guestRepository = guestRepository;
        this.keyShareRequestRepository = keyShareRequestRepository;
    }

    @Override
    public AccessLog createLog(AccessLog log) {

        if (log.getAccessTime().isAfter(Instant.now())) {
            throw new IllegalArgumentException("Access time cannot be in the future");
        }

        DigitalKey key = digitalKeyRepository.findById(log.getDigitalKey().getId())
                .orElseThrow(() -> new ResourceNotFoundException("DigitalKey not found"));

        Guest guest = guestRepository.findById(log.getGuest().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Guest not found"));

        log.setDigitalKey(key);
        log.setGuest(guest);

        boolean allowed = false;

        if (key.getBooking().getGuest().getId().equals(guest.getId())) {
            allowed = true;
        }

        if (!allowed) {
            List<KeyShareRequest> shares =
                    keyShareRequestRepository.findBySharedWithId(guest.getId());

            for (KeyShareRequest req : shares) {
                if (req.getDigitalKey().getId().equals(key.getId())
                        && "APPROVED".equals(req.getStatus())
                        && Instant.now().isAfter(req.getShareStart())
                        && Instant.now().isBefore(req.getShareEnd())) {
                    allowed = true;
                    break;
                }
            }
        }

    
        if (!key.getActive()
                || Instant.now().isBefore(key.getIssuedAt())
                || Instant.now().isAfter(key.getExpiresAt())) {
            allowed = false;
        }

        log.setResult(allowed ? "SUCCESS" : "DENIED");

        return accessLogRepository.save(log);
    }

    @Override
    public List<AccessLog> getLogsForGuest(Long guestId) {
        return accessLogRepository.findByGuestId(guestId);
    }

    @Override
    public List<AccessLog> getLogsForKey(Long keyId) {
        return accessLogRepository.findByDigitalKeyId(keyId);
    }
}
