package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Team;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.IntStream;

import static com.acme.racingteam.repository.MockDB.TEAMS;

@Repository
public class TeamRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(TeamRepository.class);

    TeamRepository() {
        // leerer Konstruktor fuer Spring
    }

    @Nullable
    public Team findById(final UUID id) {
        return TEAMS.stream()
            .filter(team -> Objects.equals(team.getId(), id))
            .findFirst()
            .orElse(null);
    }

    public Collection<Team> find(final Map<String, String> queryParam) {
        if (queryParam.isEmpty()) {
            return findAll();
        }
        if (queryParam.size() == 1) {
            final var name = queryParam.get("name");
            if (name != null) {
                return findByName(name);
            }
            final var teamPrincipal = queryParam.get("teamPrincipal");
            if (teamPrincipal != null) {
                return findByTeamPrincipal(teamPrincipal);
            }
        }
        return Collections.emptyList();
    }

    private Collection<Team> findByName(final String name) {
        return TEAMS.stream()
            .filter(team -> team.getName().toLowerCase().contains(name.toLowerCase()))
            .toList();
    }

    private Collection<Team> findByTeamPrincipal(final String teamPrincipal) {
        return TEAMS.stream()
            .filter(team -> team.getTeamPrincipal().toLowerCase().contains(teamPrincipal.toLowerCase()))
            .toList();
    }

    public Collection<Team> findAll() {
        return Collections.unmodifiableList(TEAMS);
    }

    public Team create(final Team team) {
        team.setId(UUID.randomUUID());
        TEAMS.add(team);
        LOGGER.debug("create: {}", team);
        return team;
    }

    public void update(final Team team) {
        final var index = IntStream.range(0, TEAMS.size())
            .filter(i -> Objects.equals(TEAMS.get(i).getId(), team.getId()))
            .findFirst();
        if (index.isEmpty()) {
            return;
        }
        TEAMS.set(index.getAsInt(), team);
        LOGGER.debug("update: {}", team);
    }

    public boolean isNameExisting(final String name) {
        return TEAMS.stream()
            .anyMatch(t -> t.getName().equalsIgnoreCase(name));
    }

    public void deleteById(final UUID id) {
        TEAMS.removeIf(t -> Objects.equals(t.getId(), id));
    }
}
