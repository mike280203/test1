package com.acme.racingteam.service;

import com.acme.racingteam.entity.Team;
import com.acme.racingteam.repository.*;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.InjectSoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.condition.JRE.JAVA_25;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;

@Tag("unit")
@Tag("service-write")
@DisplayName("TeamWriteService für Neuanlegen testen")
@Execution(CONCURRENT)
@EnabledForJreRange(min = JAVA_25, max = JAVA_25)
@ExtendWith(SoftAssertionsExtension.class)
@SuppressWarnings("WriteTag")
class TeamWriteServiceTest {

    private static final String NEUER_NAME = "NeuesTestTeam";
    private static final String NEUER_PRINCIPAL = "Test Principal";
    private static final String NEUE_LOCATION = "Testort";
    private static final String NEUES_LAND = "Deutschland";

    private final TeamWriteService service;
    private final TeamRepository repo;

    @InjectSoftAssertions
    @SuppressWarnings("NullAway.Init")
    private SoftAssertions softly;

    @SuppressWarnings("PMD.AvoidAccessibilityAlteration")
    @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
    TeamWriteServiceTest() {
        final var constructor = TeamRepository.class.getDeclaredConstructors()[0];
        constructor.setAccessible(true);
        try {
            repo = (TeamRepository) constructor.newInstance();
        } catch (final InstantiationException | IllegalAccessException | InvocationTargetException ex) {
            throw new IllegalStateException(ex);
        }

        service = new TeamWriteService(repo);
    }

    @ParameterizedTest(name = "[{index}] Neuanlegen eines neuen Teams: name={0}")
    @CsvSource(NEUER_NAME + "," + NEUER_PRINCIPAL + "," + NEUE_LOCATION + "," + NEUES_LAND)
    @DisplayName("Neuanlegen eines neuen Teams")
    void create(final ArgumentsAccessor args) {
        // given
        final var name = args.getString(0);
        final var teamPrincipal = args.getString(1);
        final var location = args.getString(2);
        final var country = args.getString(3);

        final var homebase = HomebaseBuilder
            .getBuilder()
            .setLocation(location)
            .setCountry(country)
            .build();

        final var drivers = List.of(
            DriverBuilder
                .getBuilder()
                .setName("Max Test")
                .setAge(30)
                .build()
        );

        final var team = TeamBuilder
            .getBuilder()
            .setName(name)
            .setTeamPrincipal(teamPrincipal)
            .setHomebase(homebase)
            .setDrivers(drivers)
            .build();

        // when
        final var teamCreated = service.create(team);

        // then
        softly.assertThat(teamCreated.getId()).isNotNull();
        softly.assertThat(teamCreated.getName()).isEqualTo(NEUER_NAME);
        softly.assertThat(teamCreated.getHomebase().getLocation()).isEqualTo(NEUE_LOCATION);
        softly.assertThat(teamCreated.getDrivers()).hasSize(1);
        softly.assertThat(teamCreated.getDrivers().get(0).getName()).isEqualTo("Max Test");
    }
}
