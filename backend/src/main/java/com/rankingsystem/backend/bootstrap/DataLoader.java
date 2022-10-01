package com.rankingsystem.backend.bootstrap;

import com.rankingsystem.backend.domain.Alliance;
import com.rankingsystem.backend.domain.Battle;
import com.rankingsystem.backend.domain.Player;
import com.rankingsystem.backend.domain.security.Authority;
import com.rankingsystem.backend.domain.security.Role;
import com.rankingsystem.backend.domain.security.User;
import com.rankingsystem.backend.repositories.AllianceRepository;
import com.rankingsystem.backend.repositories.BattleRepository;
import com.rankingsystem.backend.repositories.PlayerRepository;
import com.rankingsystem.backend.repositories.security.AuthorityRepository;
import com.rankingsystem.backend.repositories.security.RoleRepository;
import com.rankingsystem.backend.repositories.security.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final AllianceRepository allianceRepository;
    private final BattleRepository battleRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AuthorityRepository authorityRepository;
    private final PlayerRepository playerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        loadDefaultUsers();
        loadData();
    }


    private void loadDefaultUsers() {
        //beer auths
        Authority createPlayer = authorityRepository.save(Authority.builder().permission("player.create").build());
        Authority updatePlayer = authorityRepository.save(Authority.builder().permission("player.update").build());
        Authority readPlayer = authorityRepository.save(Authority.builder().permission("player.read").build());
        Authority deletePlayer = authorityRepository.save(Authority.builder().permission("player.delete").build());

        Role playerRole = roleRepository.save(Role.builder().roleName("PLAYER").build());

        Role adminRole = roleRepository.save(Role.builder().roleName("ADMIN").build());

        adminRole.setAuthorities(new HashSet<>(Set.of(createPlayer, updatePlayer, readPlayer, deletePlayer)));
        playerRole.setAuthorities(new HashSet<>(Set.of(readPlayer, createPlayer)));

        roleRepository.saveAll(Arrays.asList(playerRole, adminRole));

        //add default user
        userRepository.save(
                User.builder()
                        .username("admin")
                        .password(passwordEncoder.encode("admin"))
                        .role(adminRole)
                        .build()
        );
    }



    private void loadData() {
        Player player1 = Player.builder()
                .name("player1")
                .power(10000)
                .build();

        Player player2 = Player.builder()
                .name("player2")
                .power(1000)
                .build();
        Player player3 = Player.builder()
                .name("player3")
                .power(50000)
                .build();
        Player player4 = Player.builder()
                .name("player4")
                .power(100)
                .build();
        Player player5 = Player.builder()
                .name("player5")
                .power(30)
                .build();
        Player player6 = Player.builder()
                .name("player6")
                .power(20000)
                .build();
        Player player7 = Player.builder()
                .name("player7")
                .power(100000)
                .build();

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        playerRepository.save(player5);
        playerRepository.save(player6);
        playerRepository.save(player7);

        Alliance clan1 = Alliance.builder()
                .name("Alliance-one")
                .tag("#dsafsdaf")
                .announcement("We accept any players")
                .leader(player1)
                .territory(2000)
                .allianceGift(500)
                .members(1)
                .power(player1.getPower())
                .build();



        player1.setAlliance(clan1);
        clan1.addMember(player3);
        clan1.addMember(player4);
        player3.setAlliance(clan1);
        player4.setAlliance(clan1);



        Alliance clan2 = Alliance.builder()
                .name("Alliance-two")
                .tag("#dsafsdafl")
                .announcement("We accept any players")
                .leader(player2)
                .territory(2000)
                .allianceGift(500)
                .members(1)
                .power(player2.getPower())
                .build();

        player2.setAlliance(clan2);

        allianceRepository.save(clan1);
        allianceRepository.save(clan2);

        System.out.println("Loaded players: " + playerRepository.count());
        System.out.println("Loaded alliances: " + allianceRepository.count());

    }



}



