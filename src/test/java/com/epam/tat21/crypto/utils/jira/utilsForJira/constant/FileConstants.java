package com.epam.tat21.crypto.utils.jira.utilsForJira.constant;

public enum FileConstants {
    FILE_EXTENSION_SEPARATOR("."),
    JIRA_KEY_PREFIX("_");

    private final String value;

    FileConstants(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
