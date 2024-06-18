package com.challenge.interfaces.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.models.User;

public interface UserJPARepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
