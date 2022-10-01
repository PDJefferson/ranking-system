package com.rankingsystem.backend.web.model;

import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PlayerDto extends BaseItem {
    private String name;
    private int resource;
    private int power;
    private int donations;
    private int kills;
    private int bestContributions;

    private String allianceTag;

    @Builder
    public PlayerDto(UUID id, String name, int resource, int power, int donations, int kills, int bestContributions, String allianceTag) {
        super(id);
        this.name = name;
        this.resource = resource;
        this.power = power;
        this.donations = donations;
        this.kills = kills;
        this.bestContributions = bestContributions;
        this.allianceTag = allianceTag;
    }
}
