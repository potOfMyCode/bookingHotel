package model.util;

import java.util.Arrays;

public enum Language {
    ENG("en"),
    UKR("uk");

    private String langTag;

    Language(String langTag) {
        this.langTag = langTag;
    }

    public String getLangTag() {
        return langTag;
    }

    public static String isLangOrGetDefault(String tag) {
        Language lang = Arrays.stream(Language.values())
                .filter(a -> a.getLangTag().equals(tag))
                .findFirst()
                .orElse(Language.ENG);
        return lang.getLangTag();
    }
}
