package com.acme.racingteam.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.util.List;

@SuppressWarnings("RecordComponentNumber")
record TeamDTO(
    @NotBlank
    @Pattern(regexp = NAME_PATTERN)
    String name,

    @NotBlank
    String teamPrincipal,

    @Valid
    @NotNull(groups = OnCreate.class)
    HomebaseDTO homebase,

    @Valid
    @NotNull(groups = OnCreate.class)
    List<DriverDTO> drivers
) {
    public static final String NAME_PATTERN = "[A-Za-z0-9\\-\\s&']{2,50}";

    interface OnCreate {}
}
