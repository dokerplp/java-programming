package com.company.javaFX;

import java.util.Locale;

public enum Language {

    EN (new Locale("en", "EN")),
    RU (new Locale("ru", "RU")),
    PL(new Locale("pl", "PL")),
    EE(new Locale("et", "EE"));

    public Locale getLocale() {
        return locale;
    }

    private final Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }


}
