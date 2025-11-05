/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 *
 * Licensed under the GNU General Public License, Version 3 (GPLv3).
 */
package com.acme.racingteam.service;

import com.acme.racingteam.entity.Team;
import com.acme.racingteam.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

import java.util.Collection;

@Service
public class TeamService {
    private final TeamRepository repo;

    TeamService(final TeamRepository repo) {
        this.repo = repo;
    }

    public Team findById(final UUID id) {
        // Use Case
        final var team = repo.findById(id); // evtl. null
        if (team == null) {
            throw new NotFoundException(id);
        }

        return team;
    }


    // Alle Teams abrufen
    public Collection<Team> find(final Map<String, String> queryparam) {
        final var teams = repo.find(queryparam);
        if (teams == null) {
            throw new NotFoundException();
        }
        return teams;
    }
}
