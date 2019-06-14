package com.epam.tat21.crypto.utils.jira.utilsForJira.resultconstructor;

public class TestResultConstructorManager {
    private static volatile TestResultConstructor instance;

    private TestResultConstructorManager() {
    }

    public static synchronized TestResultConstructor getInstance() {
        if (instance == null) {
            instance = new TestResultConstructor();
        }
        return instance;
    }
}
