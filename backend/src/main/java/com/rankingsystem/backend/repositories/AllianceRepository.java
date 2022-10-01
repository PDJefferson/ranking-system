package com.rankingsystem.backend.repositories;

import com.rankingsystem.backend.domain.Alliance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface AllianceRepository extends JpaRepository<Alliance, UUID> {

    @Query(nativeQuery = true, value = "SELECT * FROM alliance " +
            "ORDER BY power DESC limit ?1")
    List<Alliance> findOrderByPowerDescLimitedTo(int limit);
}
