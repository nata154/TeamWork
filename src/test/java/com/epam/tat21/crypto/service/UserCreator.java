package com.epam.tat21.crypto.service;

import com.epam.tat21.crypto.bo.User;

public class UserCreator {

    public static User withCredentialsFromProperty() {
        return new User(TestDataReader.getUserName(),
                TestDataReader.getUserPassword());
    }

    public static User withEmptyUsername() {
        return new User("", TestDataReader.getUserPassword());
    }

    public static User withEmptyPassword() {
        return new User(TestDataReader.getUserName(), "");
    }
}
