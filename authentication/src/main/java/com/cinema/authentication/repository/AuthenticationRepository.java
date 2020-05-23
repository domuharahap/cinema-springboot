package com.cinema.authentication.repository;

import com.cinema.authentication.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthenticationRepository extends JpaRepository<Users, Long> {
    Users findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Users findByEmail(String email);

    Users findByUsernameOrEmail(String username, String email);
}
