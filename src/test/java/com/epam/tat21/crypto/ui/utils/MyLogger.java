package com.epam.tat21.crypto.ui.utils;

import com.epam.tat21.crypto.ui.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MyLogger {

    private static final Logger LOGGER = LogManager.getLogger(MyLogger.class);

    private MyLogger() {
    }

    public static void info(String message) {
        LOGGER.info("THREAD: " + Thread.currentThread().getName() +
                " DRIVER: " + DriverManager.getDriver().hashCode() +
                " MESSAGE: " + message);
    }

    public static void error(String message) {
        LOGGER.error(message);
    }

    public static void debug(String message) {
        LOGGER.debug(message);
    }

    public static void attach(String filePath, String message) {
        LOGGER.info("RP_MESSAGE#FILE#{}#{}", filePath, message);
    }
}
