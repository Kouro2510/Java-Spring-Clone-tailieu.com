package com.example.bodethi.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "failed_login_attempts")
public class FailedLoginAttemptEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "attempt_count")
    private int attemptCount;

    public FailedLoginAttemptEntity() {
    }

    public FailedLoginAttemptEntity(Long id, String ipAddress, LocalDateTime timestamp, int attemptCount) {
        this.id = id;
        this.ipAddress = ipAddress;
        this.timestamp = timestamp;
        this.attemptCount = attemptCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getAttemptCount() {
        return attemptCount;
    }

    public void setAttemptCount(int attemptCount) {
        this.attemptCount = attemptCount;
    }
}
