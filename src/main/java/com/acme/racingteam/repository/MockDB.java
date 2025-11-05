package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Team;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SuppressWarnings({"UtilityClass", "MagicNumber"})
final class MockDB {
    static final List<Team> TEAMS;

    static {
        TEAMS = Stream.of(
                TeamBuilder.getBuilder()
                        .setId(UUID.fromString("00000000-0000-0000-0000-000000000001"))
                        .setName("Oracle Red Bull Racing")
                        .setTeamPrincipal("Laurent Mekies")
                        .setHomebase(HomebaseBuilder.getBuilder()
                                .setLocation("Milton Keynes")
                                .setCountry("England")
                                .build())
                        .setDrivers(List.of(
                                DriverBuilder.getBuilder().setName("Max Verstappen").setAge(28).build(),
                                DriverBuilder.getBuilder().setName("Yuki Tsunoda").setAge(25).build()
                        ))
                        .build(),
                TeamBuilder.getBuilder()
                        .setId(UUID.fromString("00000000-0000-0000-0000-000000000002"))
                        .setName("Apex GP")
                        .setTeamPrincipal("Kim Bodnia")
                        .setHomebase(HomebaseBuilder.getBuilder()
                                .setLocation("Los Angeles")
                                .setCountry("USA")
                                .build())
                        .setDrivers(List.of(
                                DriverBuilder.getBuilder().setName("Sonny Hayes").setAge(61).build(),
                                DriverBuilder.getBuilder().setName("Joshua Pearce").setAge(24).build()
                        ))
                        .build()
        ).collect(Collectors.toList());
    }

    private MockDB() {}
}
