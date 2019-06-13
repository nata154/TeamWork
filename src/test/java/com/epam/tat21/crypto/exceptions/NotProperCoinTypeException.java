package com.epam.tat21.crypto.exceptions;

public class NotProperCoinTypeException extends RuntimeException {
    private String message = "The type of the current coin dont matches required parameters";
    public String getMessage() {
        return message;
    }
}
