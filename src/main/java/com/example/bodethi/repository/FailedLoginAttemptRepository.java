package com.example.bodethi.repository;

import com.example.bodethi.entity.FailedLoginAttemptEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FailedLoginAttemptRepository extends JpaRepository<FailedLoginAttemptEntity, Long> {
    FailedLoginAttemptEntity findByIpAddress(String ipAddress);
}