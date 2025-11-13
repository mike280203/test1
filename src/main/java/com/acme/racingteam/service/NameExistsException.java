package com.acme.racingteam.service;

import java.io.Serial;

public class NameExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = -1591444778427523090L;

    private final String name;

    public NameExistsException(final String name) {
        super("Der Teamname " + name + " existiert bereits");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
