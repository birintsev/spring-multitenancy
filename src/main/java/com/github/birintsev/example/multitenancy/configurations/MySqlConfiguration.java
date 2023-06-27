package com.github.birintsev.example.multitenancy.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.github.birintsev.example.multitenancy.repositories.mysql")
public class MySqlConfiguration {

}
