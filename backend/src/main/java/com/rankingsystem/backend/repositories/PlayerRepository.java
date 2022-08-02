package com.rankingsystem.backend.repositories;

import com.rankingsystem.backend.domain.Player;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface PlayerRepository extends CrudRepository<Player, UUID> {

}
