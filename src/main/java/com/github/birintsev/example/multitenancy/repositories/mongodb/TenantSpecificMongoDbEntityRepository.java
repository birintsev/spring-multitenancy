package com.github.birintsev.example.multitenancy.repositories.mongodb;

import com.github.birintsev.example.multitenancy.entities.mongodb.TenantSpecificMongoDbEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantSpecificMongoDbEntityRepository extends MongoRepository<TenantSpecificMongoDbEntity, ObjectId> {

}
