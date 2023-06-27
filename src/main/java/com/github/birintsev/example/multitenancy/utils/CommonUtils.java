package com.github.birintsev.example.multitenancy.utils;

import com.github.birintsev.example.multitenancy.configurations.TenantsConfigurationProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {

    public static <T> Map<String, T> createCaseInsensitiveKeysMap(Map<String, T> map) {
        TreeMap<String, T> caseInsensitiveKeysMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveKeysMap.putAll(map);
        return caseInsensitiveKeysMap;
    }

    public static <V> Map<String, V> createTenantNamesMap(boolean tenantNamesAreCaseInsensitive) {
        return tenantNamesAreCaseInsensitive ? createCaseInsensitiveKeysMap(Collections.emptyMap()) : new HashMap<>();
    }

    public static <V> Map<String, V> createTenantNamesMap(
        TenantsConfigurationProperties tenantsConfigurationProperties
    ) {
        return createTenantNamesMap(tenantsConfigurationProperties.isTenantNamesCaseInsensitive());
    }
}
