package com.github.birintsev.example.multitenancy.repositories.mysql;

import com.github.birintsev.example.multitenancy.entities.mysql.TenantSpecificMySqlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TenantSpecificMySqlEntityRepository extends JpaRepository<TenantSpecificMySqlEntity, Long> {

}
