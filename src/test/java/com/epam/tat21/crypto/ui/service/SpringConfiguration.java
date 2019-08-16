package com.epam.tat21.crypto.ui.service;

import com.epam.tat21.crypto.ui.businessObjects.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.epam.tat21.crypto")
public class SpringConfiguration {

    @Bean
    public User getUser(){
        System.out.println("SPRING GET USER");
        return UserCreator.withCredentialsFromProperty();
    }

}
