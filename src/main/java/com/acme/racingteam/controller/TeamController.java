/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 *
 * Licensed under the GNU General Public License, Version 3 (GPLv3).
 */
package com.acme.racingteam.controller;

import com.acme.racingteam.entity.Team;
import com.acme.racingteam.service.TeamService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

import java.util.Collection;

@RestController
@RequestMapping(TeamController.API_PATH)
class TeamController {
    static final String API_PATH = "/teams";
    static final String ID_PATTERN = "[\\da-f]{8}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{4}-[\\da-f]{12}";
    private final TeamService service;

    public TeamController(final TeamService service) {
        this.service = service;
    }


    @GetMapping(path = "{id:" + ID_PATTERN + "}")
    Team getById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @GetMapping
    Collection<Team> get(@RequestParam final Map<String, String> queryparam) {
        return service.find(queryparam);
    }

}
