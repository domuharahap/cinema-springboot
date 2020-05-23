package com.cinema.authentication.repository;

import com.cinema.authentication.domain.Roles;
import com.cinema.authentication.utils.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolesRepository extends JpaRepository<Roles, Long> {
    Optional<Roles> findByName(RoleName roleName);
}
