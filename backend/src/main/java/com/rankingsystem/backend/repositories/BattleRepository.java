package com.rankingsystem.backend.repositories;

import com.rankingsystem.backend.domain.Battle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BattleRepository extends JpaRepository<Battle, UUID> {

}
