package com.github.birintsev.example.multitenancy.datarouting.mysql;

import com.github.birintsev.example.multitenancy.configurations.TenantsConfigurationProperties;
import org.hibernate.engine.jdbc.connections.internal.DatasourceConnectionProviderImpl;
import org.hibernate.engine.jdbc.connections.spi.AbstractMultiTenantConnectionProvider;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static com.github.birintsev.example.multitenancy.utils.CommonUtils.createTenantNamesMap;

@Component
public class TenantConnectionProvider extends AbstractMultiTenantConnectionProvider {

    private final TenantsConfigurationProperties tenantsConfigurationProperties;

    private final Map<String, ConnectionProvider> tenantNamesToConnectionProviders;

    private final Lock providersWriteLock = new ReentrantLock();

    public TenantConnectionProvider(TenantsConfigurationProperties tenantsConfigurationProperties) {
        this.tenantsConfigurationProperties = tenantsConfigurationProperties;
        this.tenantNamesToConnectionProviders = createTenantNamesMap(tenantsConfigurationProperties);
    }

    @Override
    protected ConnectionProvider getAnyConnectionProvider() {
        return getOrCreateConnectionProvider(tenantsConfigurationProperties.getDefaultTenantName());
    }

    @Override
    protected ConnectionProvider selectConnectionProvider(String tenantName) {
        return getOrCreateConnectionProvider(tenantName);
    }

    private ConnectionProvider getOrCreateConnectionProvider(String tenantName) {
        ConnectionProvider connectionProvider = tenantNamesToConnectionProviders.get(tenantName);
        if (connectionProvider != null) {
            return connectionProvider;
        }
        return createConnectionProvider(tenantName);
    }

    private ConnectionProvider createConnectionProvider(String tenantName) {
        if (!tenantsConfigurationPropertiesContainsTenantName(tenantName)) { // TODO: throw a specific exception
            throw new IllegalArgumentException("Can not find configuration for tenant " + tenantName);
        }
        try {
            TenantsConfigurationProperties.Tenant tenant = getTenantConfigurationFromProperties(tenantName);
            providersWriteLock.lock();
            DatasourceConnectionProviderImpl datasourceConnectionProvider = new DatasourceConnectionProviderImpl();
            datasourceConnectionProvider.setDataSource(
                DataSourceBuilder.create().url(tenant.getMysqlConnectionUri()).build()
            );
            tenantNamesToConnectionProviders.put(tenantName, datasourceConnectionProvider);
            return datasourceConnectionProvider;
        } finally {
            providersWriteLock.unlock();
        }
    }

    private boolean tenantsConfigurationPropertiesContainsTenantName(String tenantName) {
        return tenantsConfigurationProperties.getTenants().containsKey(tenantName);
    }

    private TenantsConfigurationProperties.Tenant getTenantConfigurationFromProperties(String tenantName) {
        return tenantsConfigurationProperties.getTenants().get(tenantName);
    }
}
