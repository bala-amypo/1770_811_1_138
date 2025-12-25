package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.DigitalKey;
import java.util.List;
import java.util.Optional;
public interface DigitalKeyRepository extends JpaRepository<DigitalKey, Long> {

    Optional<DigitalKey> findByBookingIdAndActiveTrue(Long bookingId);


    List<DigitalKey> findByBookingGuestId(Long guestId);
}
