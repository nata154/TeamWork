package com.epam.tat21.crypto.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final String TESTDATA_USER_NAME = "testdata.user.name";
    private static final String TESTDATA_USER_PASSWORD = "testdata.user.password";
    private static final String APPLICATION_URL = "application.url";
    private static final String PROFILE_NAME = "profile.name";
    private static final String SCREENSHOT_FOLDER = "screenshot.folder";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    private TestDataReader() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

    public static String getUserName() {
        return getProperty(TESTDATA_USER_NAME);
    }

    public static String getUserPassword() {
        return getProperty(TESTDATA_USER_PASSWORD);
    }

    public static String getApplicationUrl() {
        return getProperty(APPLICATION_URL);
    }

    public static String getProfileName() {
        return getProperty(PROFILE_NAME);
    }

    public static String getScreenshotFolderPath() {
        return getProperty(SCREENSHOT_FOLDER);
    }
}
