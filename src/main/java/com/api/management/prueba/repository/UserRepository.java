package com.api.management.prueba.repository;

import com.api.management.prueba.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String user);
    Boolean existsByUsername(String user);
    Boolean existsByEmail(String email);
}