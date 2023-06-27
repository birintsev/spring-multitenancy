package com.github.birintsev.example.multitenancy.controllers;

import com.github.birintsev.example.multitenancy.dto.TenantSpecificEntityDto;
import com.github.birintsev.example.multitenancy.entities.mongodb.TenantSpecificMongoDbEntity;
import com.github.birintsev.example.multitenancy.entities.mysql.TenantSpecificMySqlEntity;
import com.github.birintsev.example.multitenancy.mappers.TenantSpecificEntityMapper;
import com.github.birintsev.example.multitenancy.services.TenantSpecificEntitiesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tenant-specific-entities")
@RequiredArgsConstructor
public class TenantSpecificEntityController {

    private final TenantSpecificEntitiesService tenantSpecificEntitiesService;

    private final TenantSpecificEntityMapper tenantSpecificEntityMapper;

    @PostMapping("/mongo-db")
    public ResponseEntity<TenantSpecificEntityDto> createInMongoDb() {
        TenantSpecificMongoDbEntity tenantSpecificMongoDbEntity = tenantSpecificEntitiesService.createMongoDbEntity();
        return ResponseEntity.ok(tenantSpecificEntityMapper.toDto(tenantSpecificMongoDbEntity));
    }

    @PostMapping("/mysql")
    public ResponseEntity<TenantSpecificEntityDto> createInMySql() {
        TenantSpecificMySqlEntity tenantSpecificMySqlEntity = tenantSpecificEntitiesService.createMySqlEntity();
        return ResponseEntity.ok(tenantSpecificEntityMapper.toDto(tenantSpecificMySqlEntity));
    }
}
