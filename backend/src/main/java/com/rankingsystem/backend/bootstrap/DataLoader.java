package com.rankingsystem.backend.bootstrap;

import com.rankingsystem.backend.domain.Player;
import com.rankingsystem.backend.repositories.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class DataLoader implements CommandLineRunner {

    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        loadData();
    }

    private void loadData() {
        Player player1 = Player.builder()
                .nickname("Player1")
                .draws(0)
                .raids(0)
                .clan("Clan1")
                .losses(10)
                .wins(20)
                .points(100)
                .rank("Diamond")
                .build();

        Player player2 = Player.builder()
                .nickname("Player2")
                .draws(10)
                .raids(20)
                .clan("Clan2")
                .losses(20)
                .wins(100)
                .points(500)
                .rank("Emerald")
                .build();

        Player player3 = Player.builder()
                .nickname("Player3")
                .draws(10)
                .raids(10)
                .clan("Clan3")
                .losses(100)
                .wins(20)
                .points(10)
                .rank("Silver")
                .build();

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);

        System.out.println("Loaded players: " + playerRepository.count());
    }


}



