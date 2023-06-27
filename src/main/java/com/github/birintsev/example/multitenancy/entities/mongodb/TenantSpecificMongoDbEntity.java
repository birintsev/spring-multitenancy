package com.github.birintsev.example.multitenancy.entities.mongodb;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "tenant_specific_entities")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TenantSpecificMongoDbEntity {

    @Id
    private ObjectId id;

    private String tenantName;

    private String entityName;
}
