package com.maspez.user_service.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maspez.user_service.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByEmail(String mailId);
}
