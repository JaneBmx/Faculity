package com.vlasova.controller.command.impl.language;

import java.util.Locale;

public enum Language {
    EN(new Locale("en", "EN")),
    RU(new Locale("ru", "RU"));
    private Locale locale;

    Language(Locale locale) {
        this.locale = locale;
    }

    public Locale getLocale() {
        return locale;
    }

    public static Language getLanguage(String language){
        for(Language lang: Language.values()){
            if (lang.equals(language)){
                return lang;
            }
        }
        return EN;
    }
}