package com.epam.tat21.crypto.exceptions;

public class JsonSchemaValidationException extends RuntimeException {
    public JsonSchemaValidationException(String errorMessage){
        super(errorMessage);
    }
}
