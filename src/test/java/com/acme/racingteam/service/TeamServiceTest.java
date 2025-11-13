package com.acme.racingteam.service;

import com.acme.racingteam.entity.Team;
import com.acme.racingteam.repository.TeamRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@Tag("unit")
@Tag("service-read")
@ExtendWith(SoftAssertionsExtension.class)
@DisplayName("Geschäftslogik für TeamService testen")
class TeamServiceTest {

    // Testdaten aus der MockDB
    private static final String ID_VORHANDEN = "00000000-0000-0000-0000-000000000001";
    private static final String NAME_VORHANDEN = "Oracle Red Bull Racing";

    private final TeamService service;

    // SoftAssertions --> mehrere Überprüfungen ohne Testabbruch bei der ersten falschen
    @InjectSoftAssertions
    @SuppressWarnings("NullAway.Init")
    private SoftAssertions softly;

    TeamServiceTest() {
        final var constructor = TeamRepository.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        final TeamRepository repo;
        try {
            repo = (TeamRepository) constructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        service = new TeamService(repo);
    }

    // TEST 1: Suche eines Teams per ID
    @ParameterizedTest(name = "[{index}] Suche mit vorhandener ID: id={0}")
    @ValueSource(strings = ID_VORHANDEN)
    @DisplayName("Suche Team mit ID")
    void findById(final String id) {
        // given ID aus String
        final var teamId = UUID.fromString(id);

        // when
        final var team = service.findById(teamId);

        // then
        assertThat(team)
                .as("Team sollte für angegebene ID gefunden werden")
                .isNotNull()
                .extracting(Team::getId)
                .isEqualTo(teamId);
    }

    // TEST 2: Suche eines Teams per Name
    @ParameterizedTest(name = "[{index}] Suche mit vorhandenem Teamnamen: name={0}")
    @ValueSource(strings = NAME_VORHANDEN)
    @DisplayName("Suche Team mit vorhandenem Namen")
    void find(final String name) {
        // given
        final var params = Map.of("name", name);

        // when
        final var teams = service.find(params);

        // then
        softly.assertThat(teams)
                .as("Ergebnisliste sollte Teams enthalten")
                .isNotNull()
                .isNotEmpty();

        teams.stream()
                .map(Team::getName)
                .forEach(teamName -> softly.assertThat(teamName)
                        .as("Gefundener Teamname sollte den Suchbegriff enthalten")
                        .contains(NAME_VORHANDEN));
    }
}
