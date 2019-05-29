package com.epam.tat21.crypto.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getUserName(String key) {
        return resourceBundle.getString(key);
    }

    public static String getUserPassword(String key) {
        return resourceBundle.getString(key);
    }
}
