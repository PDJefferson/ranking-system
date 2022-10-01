package com.rankingsystem.backend.services;

import com.rankingsystem.backend.web.model.PlayerBasicData;
import com.rankingsystem.backend.web.model.PlayerBasicDataList;
import com.rankingsystem.backend.web.model.PlayerDto;
import com.rankingsystem.backend.web.model.PlayerList;
import com.rankingsystem.backend.web.model.pagination.ObjectPagedList;
import org.springframework.data.domain.Pageable;

public interface PlayerService {

    PlayerList listPlayers();

    PlayerDto createNewPlayer(PlayerDto playerDto);

    ObjectPagedList<PlayerBasicData> listTopPlayers(Pageable pageable);

    ObjectPagedList<PlayerBasicData> playersPaginated(Pageable pageable);
}
