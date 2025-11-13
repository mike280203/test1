package com.acme.racingteam.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

record HomebaseDTO(
    @NotBlank
    String location,

    @NotBlank
    String country
) {}
