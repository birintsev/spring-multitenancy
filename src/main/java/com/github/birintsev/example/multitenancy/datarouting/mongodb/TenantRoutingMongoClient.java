package com.github.birintsev.example.multitenancy.datarouting.mongodb;

import com.github.birintsev.example.multitenancy.common.RequestHeaders;
import com.mongodb.client.MongoClient;

import java.util.Map;

import static com.github.birintsev.example.multitenancy.utils.CommonUtils.createCaseInsensitiveKeysMap;

public class TenantRoutingMongoClient extends DelegatingMongoClient {

    private final Map<String, MongoClient> tenantNamesToMongoClients;

    private final MongoClient defaultClient;

    private final RequestHeaders requestHeaders;

    public TenantRoutingMongoClient(
        Map<String, MongoClient> tenantNamesToMongoClients, MongoClient defaultClient, RequestHeaders requestHeaders
    ) {
        this.tenantNamesToMongoClients = createCaseInsensitiveKeysMap(tenantNamesToMongoClients);
        this.defaultClient = defaultClient;
        this.requestHeaders = requestHeaders;
    }

    @Override
    protected MongoClient getClientForDelegation() {
        String currentTenantName = requestHeaders.getTenantName();
        MongoClient clientForDelegation = tenantNamesToMongoClients.get(currentTenantName);
        if (clientForDelegation == null) {
            clientForDelegation = defaultClient;
        }
        return clientForDelegation;
    }
}
