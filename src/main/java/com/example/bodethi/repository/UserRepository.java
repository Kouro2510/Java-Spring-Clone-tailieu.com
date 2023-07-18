package com.example.bodethi.repository;

import com.example.bodethi.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<AccountEntity, Long> {

    Optional<AccountEntity> findByUsername(String username);

    Boolean existsByEmail(String email);

    Optional<AccountEntity> findByUsernameOrEmail(String username, String email);

    boolean existsByUsername(String username);
}