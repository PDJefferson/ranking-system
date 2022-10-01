package com.rankingsystem.backend.repositories;

import com.rankingsystem.backend.domain.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.UUID;


public interface PlayerRepository extends JpaRepository<Player, UUID> {



    @Query(nativeQuery = true , value="SELECT * FROM Player " +
            "ORDER BY power DESC limit ?1")
    List<Player> findOrderByPowerDescLimitedTo(int limit);

}
