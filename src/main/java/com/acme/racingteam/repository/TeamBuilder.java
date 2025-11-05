/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 *
 * Licensed under the GNU General Public License, Version 3 (GPLv3).
 */
package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Driver;
import com.acme.racingteam.entity.Homebase;
import com.acme.racingteam.entity.Team;

import java.util.List;
import java.util.UUID;

@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class TeamBuilder {
    private UUID id;
    private String name;
    private String teamPrincipal;
    private Homebase homebase;
    private List<Driver> drivers;

    public static TeamBuilder getBuilder() {
        return new TeamBuilder();
    }

    public TeamBuilder setId(final UUID id) {
        this.id = id;
        return this;
    }

    public TeamBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public TeamBuilder setTeamPrincipal(final String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
        return this;
    }

    public TeamBuilder setHomebase(final Homebase homebase) {
        this.homebase = homebase;
        return this;
    }

    public TeamBuilder setDrivers(final List<Driver> drivers) {
        this.drivers = drivers;
        return this;
    }

    public Team build() {
        return new Team(id, name, teamPrincipal, homebase, drivers);
    }
}
