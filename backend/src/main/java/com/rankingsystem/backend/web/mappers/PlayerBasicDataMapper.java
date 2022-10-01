package com.rankingsystem.backend.web.mappers;

import com.rankingsystem.backend.domain.Player;
import com.rankingsystem.backend.web.model.PlayerBasicData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public abstract class PlayerBasicDataMapper {

    @Mapping(source = "player", target = "allianceTag", qualifiedByName = "setAllianceTag")
    public abstract PlayerBasicData playerToPlayerDto(Player player);

    @Named("setAllianceTag")
    protected String setAllianceTag(Player player) {
        if(player.getAlliance() != null){
            return player.getAlliance().getTag();
        }
        return "no clan";
    }
}
