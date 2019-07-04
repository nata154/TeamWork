package com.epam.tat21.crypto.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static final String TESTDATA_USER_NAME = "testdata.user.name";
    private static final String TESTDATA_USER_PASSWORD = "testdata.user.password";
    private static final String APPLICATION_URL = "application.url";
    private static final String PROFILE_NAME = "profile.name";
    private static final String SCREENSHOT_FOLDER = "screenshot.folder";
    private static final String HUB_URL = "hubURL";
    private static final String SAUCE_USERNAME = "sauce.username";
    private static final String SAUCE_ACCESS_KEY = "sause.access.key";
    private static final String API_GET_URL = "api.get.url";
    private static final String JSON_SCHEMAS_LOCATION = "json.schemas.location";
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

    public static String getHubURL() {
        return getProperty(HUB_URL);
    }

    public static String getSauceUsername() {
        return getProperty(SAUCE_USERNAME);
    }

    public static String getSauceAccessKey() {
        return getProperty(SAUCE_ACCESS_KEY);
    }

    public static String getApiGetUrl() {
        return getProperty(API_GET_URL);
    }

    public static String getJsonSchemasLocation() {
        return getProperty(JSON_SCHEMAS_LOCATION);
    }
}
