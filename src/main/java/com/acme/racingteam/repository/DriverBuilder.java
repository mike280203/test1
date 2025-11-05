/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 *
 * Licensed under the GNU General Public License, Version 3 (GPLv3).
 */
package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Driver;

@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class DriverBuilder {
    private String name;
    private int age;

    public static DriverBuilder getBuilder() {
        return new DriverBuilder();
    }

    public DriverBuilder setName(final String name) {
        this.name = name;
        return this;
    }

    public DriverBuilder setAge(final int age) {
        this.age = age;
        return this;
    }

    public Driver build() {
        return new Driver(name, age);
    }
}
