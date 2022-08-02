package com.rankingsystem.backend.web.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerDto extends BaseItem {
    private String nickname;
    private int raids;
    private int wins;
    private int losses;
    private int draws;
    private int points;
    private String rank;

    private String clan;

    @Builder
    public PlayerDto(UUID id, String nickname, int raids, int wins, int losses,
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
