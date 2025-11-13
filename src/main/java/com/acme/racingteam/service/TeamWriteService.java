package com.acme.racingteam.service;

import com.acme.racingteam.entity.Team;
import com.acme.racingteam.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
public class TeamWriteService {
    private final TeamRepository repo;

    TeamWriteService(final TeamRepository repo) {
        this.repo = repo;
    }

    public Team create(final Team team) {
        if (repo.isNameExisting(team.getName())) {
            throw new NameExistsException(team.getName());
        }
        return repo.create(team);
    }

    public void update(final Team team, final UUID id) {
        final var existing = repo.findById(id);
        if (existing == null) {
            throw new NotFoundException(id);
        }
        if (!Objects.equals(team.getName(), existing.getName()) && repo.isNameExisting(team.getName())) {
            throw new NameExistsException(team.getName());
        }
        team.setId(id);
        repo.update(team);
    }
}
