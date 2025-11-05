/*
 * Copyright (C) 2025 - present
 * Hochschule Karlsruhe
 *
 * Licensed under the GNU General Public License, Version 3 (GPLv3).
 */
package com.acme.racingteam.repository;

import com.acme.racingteam.entity.Homebase;

@SuppressWarnings({"NullAway.Init", "NotNullFieldNotInitialized", "PMD.AtLeastOneConstructor"})
public class HomebaseBuilder {
    private String location;
    private String country;

    public static HomebaseBuilder getBuilder() {
        return new HomebaseBuilder();
    }

    public HomebaseBuilder setLocation(final String location) {
        this.location = location;
        return this;
    }

    public HomebaseBuilder setCountry(final String country) {
        this.country = country;
        return this;
    }

    public Homebase build() {
        return new Homebase(location, country);
    }
}
