package com.rankingsystem.backend.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Player extends BaseEntity {

    private String nickname;
    private int raids;
    private int wins;
    private int losses;
    private int draws;
    private int points;
    private String rank;

    private String clan;

    @Builder
    public Player(UUID id, String nickname, int raids, int wins, int losses,
                  int draws, int points, String rank, String clan) {
        super(id);
        this.nickname = nickname;
        this.raids = raids;
        this.wins = wins;
        this.losses = losses;
        this.draws = draws;
        this.points = points;
        this.rank = rank;
        this.clan = clan;
    }
}
