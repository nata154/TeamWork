package com.epam.tat21.crypto.utils;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private DateUtils() {
    }

    public static String getCurrentTimeAsString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd_HH-mm-ss");
        return ZonedDateTime.now().format(formatter);
    }
}
