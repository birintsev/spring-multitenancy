package com.github.birintsev.example.multitenancy.configurations;

import com.github.birintsev.example.multitenancy.common.RequestHeaders;
import com.github.birintsev.example.multitenancy.datarouting.mongodb.TenantRoutingMongoClient;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.*;

import static com.github.birintsev.example.multitenancy.utils.CommonUtils.createTenantNamesMap;

@Configuration
@EnableMongoRepositories(basePackages = "com.github.birintsev.example.multitenancy.repositories")
@RequiredArgsConstructor
public class MongoDbConfiguration {

    private final TenantsConfigurationProperties tenantsConfigurationProperties;

    @Bean
    public MongoClient mongoClient(RequestHeaders requestHeaders) { // TODO: decompose method
        Map<String, MongoClient> tenantNamesToMongoClients = createTenantNamesMap(tenantsConfigurationProperties);

        Optional.of(tenantsConfigurationProperties)
            .map(TenantsConfigurationProperties::getTenants)
            .map(Map::entrySet)
            .orElse(Collections.emptySet())
            .forEach(
                tenantConfiguration -> tenantNamesToMongoClients.put(
                    tenantConfiguration.getKey(),
                    MongoClients.create(tenantConfiguration.getValue().getMongodbConnectionUri())
                )
            );

        return new TenantRoutingMongoClient(
            tenantNamesToMongoClients,
            Optional.of(tenantsConfigurationProperties)
                .map(TenantsConfigurationProperties::getDefaultTenantName)
                .map(tenantNamesToMongoClients::get)
                .orElse(null),
            requestHeaders
        );
    }

    /*@Value("${tenants.tenants-list.first-tenant.mongodb.connection-uri}")
    private final String firstTenantConnectionUri;

    @Value("${tenants.tenants-list.second-tenant.mongodb.connection-uri}")
    private final String secondTenantConnectionUri;

    @Value("${tenants.tenants-list.first-tenant.name}")
    private final String firstTenantName;

    @Value("${tenants.tenants-list.second-tenant.name}")
    private final String secondTenantName;

    @Bean
    public MongoClient firstTenantMongoClient() {
        return MongoClients.create(firstTenantConnectionUri);
    }

    @Bean
    public MongoClient secondTenantMongoClient() {
        return MongoClients.create(secondTenantConnectionUri);
    }

    @Bean
    @Primary
    public MongoClient mongoClient(
        MongoClient firstTenantMongoClient, MongoClient secondTenantMongoClient, RequestHeaders requestHeaders
    ) {
        Map<String, MongoClient> tenantNamesToMongoClients = new HashMap<>();
        tenantNamesToMongoClients.put(firstTenantName, firstTenantMongoClient);
        tenantNamesToMongoClients.put(secondTenantName, secondTenantMongoClient);
        return new TenantRoutingMongoClient(tenantNamesToMongoClients, requestHeaders);
    }*/
}
