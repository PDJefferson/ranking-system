package com.rankingsystem.backend.web.controllers.api;


import com.rankingsystem.backend.services.PlayerService;
import com.rankingsystem.backend.web.model.PlayerDto;
import com.rankingsystem.backend.web.model.PlayerList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/v1/players")
@RestController
public class RestPlayerController {
    private final PlayerService playerService;

    @PreAuthorize("hasAuthority('player.read')")
    @GetMapping
    public ResponseEntity<PlayerList> listPlayers() {
        log.debug("getting list of Players in RestPlayerController");
        PlayerList playerList = playerService.listPlayers();
        if (playerList.getPlayers().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerList, HttpStatus.OK);
    }

    @PreAuthorize("hasAuthority('player.create')")
    @PostMapping
    public ResponseEntity<PlayerDto> createPlayer(@RequestBody PlayerDto playerDto) {
        log.debug("creating new Player");
        if (playerDto == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(playerService.createNewPlayer(playerDto), HttpStatus.CREATED);
    }
}
