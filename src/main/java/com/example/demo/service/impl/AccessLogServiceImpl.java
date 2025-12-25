package com.example.demo.service.impl;

import com.example.demo.model.AccessLog;
import com.example.demo.repository.AccessLogRepository;
import com.example.demo.service.AccessLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Service
public class AccessLogServiceImpl implements AccessLogService {

    @Autowired
    private AccessLogRepository repo;

    // 🔥 REQUIRED BY SPRING
    public AccessLogServiceImpl() {
    }

    @Override
    public AccessLog createLog(AccessLog log) {
        if (log.getAccessTime().isAfter(Instant.now())) {
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
