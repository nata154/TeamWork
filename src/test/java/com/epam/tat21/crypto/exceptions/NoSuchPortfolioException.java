package com.epam.tat21.crypto.exceptions;

public class NoSuchPortfolioException extends Exception {
    public String getMessage() {
        return "There are no portfolio with current name";
    }
}
