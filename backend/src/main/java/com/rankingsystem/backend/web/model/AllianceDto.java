package com.rankingsystem.backend.web.model;

import com.rankingsystem.backend.domain.Player;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AllianceDto extends BaseItem{

    private String name;
    private String tag;
    private String announcement;

    private Player leader;

    private int territory;
    private int allianceGift;
    private int members;
    private int power;
    @Lob
    private Byte[] image;

    private Set<Player> players = new HashSet<>();

    @Builder
    public AllianceDto(UUID id, String name, String tag, String announcement, Player leader, int territory, int allianceGift, int members, int power, Byte[] image, Set<Player> players) {
        super(id);
        this.name = name;
        this.tag = tag;
        this.announcement = announcement;
        this.leader = leader;
        this.territory = territory;
        this.allianceGift = allianceGift;
        this.members = members;
        this.power = power;
        this.image = image;
        this.players = players;
    }
}
