/*
 * Copyright (C) 2022 - present Juergen Zimmermann, Hochschule Karlsruhe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.acme.racingteam.entity;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Team {

    private UUID id;
    private String name;
    private String teamPrincipal;
    private Homebase homebase;
    private List<Driver> drivers;

    public Team(final UUID id, final String name, final String teamPrincipal,
                final Homebase homebase, final List<Driver> drivers) {
        this.id = id;
        this.name = name;
        this.teamPrincipal = teamPrincipal;
        this.homebase = homebase;
        this.drivers = drivers;
    }

    public Team() {}

//Getter Setter

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getTeamPrincipal() {
        return teamPrincipal;
    }

    public void setTeamPrincipal(final String teamPrincipal) {
        this.teamPrincipal = teamPrincipal;
    }

    public Homebase getHomebase() {
        return homebase;
    }

    public void setHomebase(final Homebase homebase) {
        this.homebase = homebase;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(final List<Driver> drivers) {
        this.drivers = drivers;
    }

//equals, hash, toString

    @Override
    public boolean equals(final Object other) {
        return other instanceof Team team && Objects.equals(id, team.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teamPrincipal='" + teamPrincipal + '\'' +
                ", homebase=" + homebase +
                ", drivers=" + drivers +
                '}';
    }
}
