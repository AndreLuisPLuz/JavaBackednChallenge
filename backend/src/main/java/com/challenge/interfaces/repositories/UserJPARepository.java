package com.challenge.interfaces.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.models.User;

public interface UserJPARepository extends JpaRepository<User, Long> {
    List<User> findByUsername(String username);
}
