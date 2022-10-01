package com.rankingsystem.backend.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Battle extends BaseEntity {

    private int troops;
    private int heal;
    private int dead;
    private int severelyWounded;
    private int slightlyWounded;
    private int remaining;

    @ManyToOne
    private Player player;

    @Builder
    public Battle(UUID id, int troops, int heal, int dead, int severelyWounded, int slightlyWounded, int remaining) {
        super(id);
        this.troops = troops;
        this.heal = heal;
        this.dead = dead;
        this.severelyWounded = severelyWounded;
        this.slightlyWounded = slightlyWounded;
        this.remaining = remaining;
    }
}