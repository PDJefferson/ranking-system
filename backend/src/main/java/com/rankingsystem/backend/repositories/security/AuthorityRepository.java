package com.rankingsystem.backend.repositories.security;

import com.rankingsystem.backend.domain.security.Authority;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AuthorityRepository extends JpaRepository<Authority, Integer> {

}
