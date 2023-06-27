package com.github.birintsev.example.multitenancy.services.impl;

import com.github.birintsev.example.multitenancy.common.RequestHeaders;
import com.github.birintsev.example.multitenancy.entities.mongodb.TenantSpecificMongoDbEntity;
import com.github.birintsev.example.multitenancy.entities.mysql.TenantSpecificMySqlEntity;
import com.github.birintsev.example.multitenancy.repositories.mongodb.TenantSpecificMongoDbEntityRepository;
import com.github.birintsev.example.multitenancy.repositories.mysql.TenantSpecificMySqlEntityRepository;
import com.github.birintsev.example.multitenancy.services.TenantSpecificEntitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantSpecificEntitiesServiceImpl implements TenantSpecificEntitiesService {

    private final TenantSpecificMongoDbEntityRepository tenantSpecificMongoDbEntityRepository;

    private final TenantSpecificMySqlEntityRepository tenantSpecificMySqlEntityRepository;

    private final RequestHeaders requestHeaders;

    @Value("${tenants.entity-name-prefix:This is an auto-generated entity name}")
    private final String entityNamePrefix;

    @Override
    public TenantSpecificMongoDbEntity createMongoDbEntity() {
        TenantSpecificMongoDbEntity tenantSpecificMongoDbEntity = new TenantSpecificMongoDbEntity(
            null,
            requestHeaders.getTenantName(),
            generateEntityName()
        );
        return tenantSpecificMongoDbEntityRepository.save(tenantSpecificMongoDbEntity);
    }

    @Override
    public TenantSpecificMySqlEntity createMySqlEntity() {
        TenantSpecificMySqlEntity tenantSpecificMySqlEntity = new TenantSpecificMySqlEntity(
            null, requestHeaders.getTenantName(), generateEntityName()
        );
        return tenantSpecificMySqlEntityRepository.save(tenantSpecificMySqlEntity);
    }

    private String generateEntityName() {
        return entityNamePrefix + " " + System.currentTimeMillis();
    }
}
