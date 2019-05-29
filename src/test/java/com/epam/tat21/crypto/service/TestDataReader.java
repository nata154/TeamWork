package com.epam.tat21.crypto.service;

import java.util.ResourceBundle;

public class TestDataReader {
    public static final String TESTDATA_USER_NAME = "testdata.user.name";
    public static final String TESTDATA_USER_PASSWORD = "testdata.user.password";
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getProperty(String key){
        return resourceBundle.getString(key);
    }

    public static String getUserName() {
        return getProperty(TESTDATA_USER_NAME);
    }

    public static String getUserPassword() {
        return getProperty(TESTDATA_USER_PASSWORD);
    }
}
