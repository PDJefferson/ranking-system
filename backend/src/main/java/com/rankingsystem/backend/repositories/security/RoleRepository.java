package com.rankingsystem.backend.repositories.security;

import com.rankingsystem.backend.domain.security.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Optional<Role> findByRoleName(String roleName);

}
