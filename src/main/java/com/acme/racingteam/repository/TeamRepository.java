package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Team;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.*;

import static com.acme.racingteam.repository.MockDB.TEAMS;

@Repository
public class TeamRepository {

    public TeamRepository() {}

    @Nullable
    public Team findById(final UUID id) {
        final var result = TEAMS.stream()
                .filter(team -> Objects.equals(team.getId(), id))
                .findFirst()
                .orElse(null);
        return result;
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
                .filter(team -> team.getName().contains(name))
                .toList();
    }

    private Collection<Team> findByTeamPrincipal(final String teamPrincipal) {
        return TEAMS.stream()
                .filter(team -> team.getTeamPrincipal().contains(teamPrincipal))
                .toList();
    }

    public Collection<Team> findAll() {
        return TEAMS;
    }

}
