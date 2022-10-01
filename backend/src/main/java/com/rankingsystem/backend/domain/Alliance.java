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
public class Alliance extends  BaseEntity{

    private String name;
    private String tag;
    private String announcement;


    @OneToOne
    private Player leader;

    private int territory;
    private int allianceGift;
    private int members;

    private int power;
    @Lob
    private Byte[] image;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "alliance")
    private Set<Player> players = new HashSet<>();

    @Builder
    public Alliance(UUID id, String name, String tag, String announcement, Player leader, int territory,
                    int allianceGift, int members, Byte[] image, Set<Player> players, int power) {
        super(id);
        this.name = name;
        this.tag = tag;
        this.announcement = announcement;
        this.leader = leader;
        this.territory = territory;
        this.allianceGift = allianceGift;
        this.members = members;
        this.image = image;
        this.power = power;
        this.players = players;
    }

    public void incrementPower(int nPower) {
        power += nPower;
        members++;
    }

    public void decrementPower(int nPower) {
        power -=  nPower;
        members--;

    }



    public void addMember(Player player) {
        if(player != null){
            incrementPower(player.getPower());
        }

    }

    public void removeMember(Player player){
        if(player != null){
            decrementPower(player.getPower());
        }

    }
}