package com.github.birintsev.example.multitenancy.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TenantSpecificEntityDto {

    private String id;

    private String tenantName;

    private String entityName;
}
