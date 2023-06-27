package com.github.birintsev.example.multitenancy.configurations;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

import javax.annotation.PostConstruct;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.Map;

import static com.github.birintsev.example.multitenancy.utils.CommonUtils.createTenantNamesMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ConfigurationProperties(prefix = "tenants-config")
@Validated
public class TenantsConfigurationProperties {

    @NotEmpty
    private Map<String, Tenant> tenants;

    private String defaultTenantName;

    private boolean tenantNamesCaseInsensitive = true;

    @PostConstruct
    private void postConstruct() {
        ensureDefaultTenantNameInTenantsListOrThrow();
        if (tenantNamesCaseInsensitive) {
            setupTenantListCaseInsensitive();
        }
    }

    private void ensureDefaultTenantNameInTenantsListOrThrow() {
        boolean tenantsListContainsDefault = tenants.containsKey(defaultTenantName);
        if (!tenantsListContainsDefault) {
            throw new IllegalArgumentException("Can not find " + defaultTenantName + " among configured tenants list");
        }
    }

    private void setupTenantListCaseInsensitive() {
        Map<String, Tenant> tenantTreeMap = createTenantNamesMap(true);
        tenantTreeMap.putAll(tenants);
        if (tenantTreeMap.size() != tenants.size()) {
            throw new IllegalArgumentException(
                "Tenants list contains case-insensitive duplicates. " +
                    "Consider either disabling case-insensibility or renaming the duplicates"
            );
        }
        tenants = tenantTreeMap;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Validated
    public static class Tenant {

        @NotBlank
        private String mongodbConnectionUri;

        @NotBlank
        private String mysqlConnectionUri;
    }
}
