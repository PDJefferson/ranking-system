package com.rankingsystem.backend.web.mappers;

import com.rankingsystem.backend.domain.Alliance;
import com.rankingsystem.backend.domain.Player;
import com.rankingsystem.backend.web.model.AllianceDto;
import com.rankingsystem.backend.web.model.PlayerDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.HashSet;


@Mapper(componentModel = "spring")
public abstract class PlayerMapper {

    @Mapping(source = "player", target = "allianceTag", qualifiedByName = "setAllianceTag")
    public abstract PlayerDto playerToPlayerDto(Player player);

    public abstract Player playerDtoToPlayer(PlayerDto playerDto);

    @Named("setAllianceTag")
    protected String setAllianceTag(Player player) {
        if(player.getAlliance() == null){
            return "No clan";
        }
        return player.getAlliance().getTag();
    }

}
