package com.vlasova.command.impl.language;

import java.util.Locale;

public enum Language {
    EN(Locale.US),
    RU(new Locale("ru", "RU"));
    private Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}