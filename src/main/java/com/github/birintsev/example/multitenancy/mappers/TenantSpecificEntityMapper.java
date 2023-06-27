package com.github.birintsev.example.multitenancy.mappers;

import com.github.birintsev.example.multitenancy.dto.TenantSpecificEntityDto;
import com.github.birintsev.example.multitenancy.entities.mongodb.TenantSpecificMongoDbEntity;
import com.github.birintsev.example.multitenancy.entities.mysql.TenantSpecificMySqlEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface TenantSpecificEntityMapper {

    @Mapping(
        target = "id",
        expression = "java(tenantSpecificMongoDbEntity == null ? null : tenantSpecificMongoDbEntity.getId().toString())"
    )
    TenantSpecificEntityDto toDto(TenantSpecificMongoDbEntity tenantSpecificMongoDbEntity);

    @Mapping(
        target = "id",
        expression = "java(tenantSpecificMySqlEntity == null ? null : tenantSpecificMySqlEntity.getId().toString())"
    )
    TenantSpecificEntityDto toDto(TenantSpecificMySqlEntity tenantSpecificMySqlEntity);
}
