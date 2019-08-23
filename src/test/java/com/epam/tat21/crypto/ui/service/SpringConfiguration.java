package com.epam.tat21.crypto.ui.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;

@Configuration
@ComponentScan(basePackages = "com.epam.tat21.crypto")
public class SpringConfiguration {

    @Bean
    public PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer properties = new PropertySourcesPlaceholderConfigurer();
        properties.setLocation(new FileSystemResource("src/test/resources/" + System.getProperty("environment") + ".properties"));
        properties.setIgnoreResourceNotFound(false);
        return properties;
    }

}
