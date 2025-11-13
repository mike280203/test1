package com.acme.racingteam.controller;

import com.acme.racingteam.entity.Driver;
import com.acme.racingteam.entity.Homebase;
import com.acme.racingteam.entity.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
interface TeamMapper {
    @Mapping(target = "id", ignore = true)
    Team toTeam(TeamDTO dto);

    Homebase toHomebase(HomebaseDTO dto);

    Driver toDriver(DriverDTO dto);
}
