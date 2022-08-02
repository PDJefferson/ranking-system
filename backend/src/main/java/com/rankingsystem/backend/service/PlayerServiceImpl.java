package com.rankingsystem.backend.service;

import com.rankingsystem.backend.repositories.PlayerRepository;
import com.rankingsystem.backend.web.mappers.PlayerMapper;
import com.rankingsystem.backend.web.model.PlayerDto;
import com.rankingsystem.backend.web.model.PlayerList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    @Override
    public PlayerList listPlayers() {
        log.debug("getting list of Players");
        List<PlayerDto> playerDtoList = new ArrayList<>();
        playerRepository.findAll().forEach(player ->
                playerDtoList.add(playerMapper.playerToPlayerDto(player)));

        return playerDtoList.isEmpty() ? new PlayerList() : new PlayerList(playerDtoList);
    }

    @Override
    public PlayerDto createNewPlayer(PlayerDto playerDto) {
        return playerMapper.playerToPlayerDto(playerRepository.save(playerMapper.playerDtoToPlayer(playerDto)));
    }
}
