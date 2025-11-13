package com.acme.racingteam.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

record DriverDTO(
    @NotBlank
    String name,

    @Positive
    int age
) {}
