package com.rankingsystem.backend.service;

import com.rankingsystem.backend.web.model.PlayerDto;
import com.rankingsystem.backend.web.model.PlayerList;

public interface PlayerService {

    PlayerList listPlayers();

    PlayerDto createNewPlayer(PlayerDto playerDto);
}
