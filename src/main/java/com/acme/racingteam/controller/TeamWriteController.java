/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 */
package com.acme.racingteam.controller;

import com.acme.racingteam.service.NameExistsException;
import com.acme.racingteam.service.TeamWriteService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.created;

@Controller
@RequestMapping(TeamController.API_PATH)
@Validated
class TeamWriteController {

    private final TeamWriteService service;
    private final TeamMapper mapper;

    TeamWriteController(final TeamWriteService service, final TeamMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping
    ResponseEntity<Void> post(
        @RequestBody @Validated({Default.class, TeamDTO.OnCreate.class}) final TeamDTO teamDTO,
        final HttpServletRequest request
    ) {
        final var teamInput = mapper.toTeam(teamDTO);
        final var team = service.create(teamInput);
        final var baseUri = request.getRequestURL().toString();
        final var location = URI.create(baseUri + '/' + team.getId());
        return created(location).build();
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    void put(
        @PathVariable final UUID id,
        @RequestBody @Valid final TeamDTO teamDTO
    ) {
        final var teamInput = mapper.toTeam(teamDTO);
        service.update(teamInput, id);
    }

    @ExceptionHandler
    ErrorResponse onNameExists(final NameExistsException ex) {
        return ErrorResponse.create(ex, UNPROCESSABLE_ENTITY, ex.getMessage());
    }

    @ExceptionHandler
    ErrorResponse onMessageNotReadable(final HttpMessageNotReadableException ex) {
        final var msg = ex.getMessage() == null ? "Invalid JSON" : ex.getMessage();
        return ErrorResponse.create(ex, BAD_REQUEST, msg);
    }

    @ExceptionHandler
    ErrorResponse onConstraintViolations(final MethodArgumentNotValidException ex) {
        final var detail = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(err -> err.getField() + ": " + err.getDefaultMessage())
            .toList()
            .toString();
        return ErrorResponse.create(ex, UNPROCESSABLE_ENTITY, detail);
    }
}
