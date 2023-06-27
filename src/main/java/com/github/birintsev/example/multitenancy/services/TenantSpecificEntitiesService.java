package com.github.birintsev.example.multitenancy.services;

import com.github.birintsev.example.multitenancy.entities.mongodb.TenantSpecificMongoDbEntity;
import com.github.birintsev.example.multitenancy.entities.mysql.TenantSpecificMySqlEntity;

public interface TenantSpecificEntitiesService {

    TenantSpecificMongoDbEntity createMongoDbEntity();

    TenantSpecificMySqlEntity createMySqlEntity();
}
