package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.AccessLog;
import java.util.List;

public interface AccessLogRepository extends JpaRepository<AccessLog, Long> {

    List<AccessLog> findByDigitalKeyId(Long keyId);

    List<AccessLog> findByGuestId(Long guestId);
}
