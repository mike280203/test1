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
    private final TeamService service;

    TeamController(final TeamService service) {
        this.service = service;
    }

    @GetMapping(path = "{id}")
    Team getById(@PathVariable final UUID id) {
        return service.findById(id);
    }

    @GetMapping
    Collection<Team> get(@RequestParam final Map<String, String> queryparam) {
        return service.find(queryparam);
    }

}
