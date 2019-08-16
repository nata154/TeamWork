package com.epam.tat21.crypto.ui.service;

import com.epam.tat21.crypto.ui.businessObjects.User;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class UserCreator {

    private UserCreator() {
    }

    @Bean
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
