package com.rankingsystem.backend.services;

import com.rankingsystem.backend.domain.Player;
import com.rankingsystem.backend.repositories.PlayerRepository;
import com.rankingsystem.backend.web.mappers.PlayerBasicDataMapper;
import com.rankingsystem.backend.web.mappers.PlayerMapper;
import com.rankingsystem.backend.web.model.PlayerBasicData;
import com.rankingsystem.backend.web.model.PlayerBasicDataList;
import com.rankingsystem.backend.web.model.PlayerDto;
import com.rankingsystem.backend.web.model.PlayerList;
import com.rankingsystem.backend.web.model.pagination.ObjectPagedList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository;

    private final PlayerMapper playerMapper;

    private final PlayerBasicDataMapper playerBSMapper;

    @Override
    public PlayerList listPlayers() {
        log.debug("getting list of Players");
        PlayerList playerDtoList = new PlayerList();
        playerRepository.findAll().forEach(player ->
                playerDtoList.getPlayers().add(playerMapper.playerToPlayerDto(player)));

        return playerDtoList;
    }

    @Override
    public PlayerDto createNewPlayer(PlayerDto playerDto) {
        return playerMapper.playerToPlayerDto(playerRepository.save(playerMapper.playerDtoToPlayer(playerDto)));
    }

    @Override
    public ObjectPagedList<PlayerBasicData> listTopPlayers(Pageable pageable) {
        Page<Player> playerPageOptional = playerRepository.findAll(pageable);

        return new ObjectPagedList<>(playerPageOptional
                .stream()
                .map(playerBSMapper::playerToPlayerDto)
                .collect(Collectors.toList()), PageRequest.of(
                playerPageOptional.getPageable().getPageNumber(),
                playerPageOptional.getPageable().getPageSize(),
                playerPageOptional.getSort()),
                playerPageOptional.getTotalElements());
    }

    @Override
    public ObjectPagedList<PlayerBasicData> playersPaginated(Pageable pageable) {
        Page<Player> playerPageOptional = playerRepository.findAll(pageable);


        //creates a list of playerBasicData, page entity, amount of elements in the page
        return new ObjectPagedList<>(playerPageOptional
                .stream()
                .map(playerBSMapper::playerToPlayerDto)
                .collect(Collectors.toList()), PageRequest.of(
                playerPageOptional.getPageable().getPageNumber(),
                playerPageOptional.getPageable().getPageSize()),
                playerPageOptional.getTotalElements());
    }


}
