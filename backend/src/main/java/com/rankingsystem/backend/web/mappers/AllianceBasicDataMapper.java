package com.rankingsystem.backend.web.mappers;

import com.rankingsystem.backend.domain.Alliance;
import com.rankingsystem.backend.web.model.AllianceBasicData;
import com.rankingsystem.backend.web.model.PlayerBasicData;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class AllianceBasicDataMapper {

    public abstract AllianceBasicData allianceToAllianceBasicData(Alliance alliance);
}
