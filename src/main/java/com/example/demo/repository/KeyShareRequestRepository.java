package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.model.KeyShareRequest;
import java.util.List;

public interface KeyShareRequestRepository extends JpaRepository<KeyShareRequest, Long> {

    List<KeyShareRequest> findBySharedById(Long guestId);

    List<KeyShareRequest> findBySharedWithId(Long guestId);
}
