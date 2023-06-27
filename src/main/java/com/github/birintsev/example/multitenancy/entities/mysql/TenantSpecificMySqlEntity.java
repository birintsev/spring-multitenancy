package com.github.birintsev.example.multitenancy.entities.mysql;

import lombok.*;

import javax.persistence.*;

@Table(name = "tenant_specific_entities")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TenantSpecificMySqlEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tenantName;

    private String entityName;
}
