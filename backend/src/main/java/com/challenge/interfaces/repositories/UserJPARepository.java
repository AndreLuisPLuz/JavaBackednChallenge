package com.challenge.interfaces.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.models.User;

public interface UserJPARepository extends JpaRepository<User, Long> {
    
}
