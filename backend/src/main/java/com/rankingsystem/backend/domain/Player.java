package com.rankingsystem.backend.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter

@Entity
public class Player extends BaseEntity {

    private String name;


    @OneToMany(cascade = CascadeType.ALL,  mappedBy = "player")
    private Set<Battle> battles = new HashSet<>();
    private int power;

    private int donations;


    private int kills ;

    private int bestContribution;


    private int resource ;

    private int victories;
    @ManyToOne(fetch = FetchType.LAZY)
    private Alliance alliance;


    @Builder
    public Player(UUID id, String name, Set<Battle> battles, int power, int donations, int kills, int bestContribution, int resource, int victories, Alliance alliance) {
        super(id);
        this.name = name;
        this.battles = battles;
        this.power = power;
        this.donations = donations;
        this.kills = kills;
        this.bestContribution = bestContribution;
        this.resource = resource;
        this.victories = victories;
        this.alliance = alliance;
    }






}
