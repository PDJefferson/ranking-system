package com.rankingsystem.backend.web.model;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@NoArgsConstructor
@Setter
@Getter
public class PlayerBasicData extends BaseItem {

    private String name;

    private int power;

    private String allianceTag;

    @Builder
    public PlayerBasicData(UUID id, String name, int power, String allianceTag) {
        super(id);
        this.name = name;
        this.power = power;
        this.allianceTag = allianceTag;
    }
}
