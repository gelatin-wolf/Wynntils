package com.wynntils.modules.utilities.enums;

public enum LanguageType {
    English("en_us.lang"),
    Korean("ko_kr.lang");

    String filename;

    LanguageType(String filename) {
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }
}
