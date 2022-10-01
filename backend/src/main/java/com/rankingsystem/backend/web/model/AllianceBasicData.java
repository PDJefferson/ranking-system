package com.rankingsystem.backend.web.model;


import lombok.*;

import java.util.UUID;


@NoArgsConstructor
@Setter
@Getter
public class AllianceBasicData extends BaseItem{

    private String name;
    private int territory;
    private int members;
    private int power;

    @Builder
    public AllianceBasicData(UUID id, String name, int territory, int members, int power) {
        super(id);
        this.name = name;
        this.territory = territory;
        this.members = members;
        this.power = power;
    }
}
