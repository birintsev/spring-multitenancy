package com.github.birintsev.example.multitenancy;

import com.github.birintsev.example.multitenancy.configurations.TenantsConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(TenantsConfigurationProperties.class)
public class ExampleMultitenancyApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExampleMultitenancyApplication.class, args);
    }
}
