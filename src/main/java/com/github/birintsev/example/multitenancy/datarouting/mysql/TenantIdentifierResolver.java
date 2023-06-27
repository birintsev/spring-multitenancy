package com.github.birintsev.example.multitenancy.datarouting.mysql;

import com.github.birintsev.example.multitenancy.common.RequestHeaders;
import lombok.RequiredArgsConstructor;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@RequiredArgsConstructor
@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

    private final RequestHeaders requestHeaders;

    @Override
    public String resolveCurrentTenantIdentifier() {
        return requestHeaders.getTenantName();
    }

    @Override
    public boolean validateExistingCurrentSessions() {
        return true;
    }
}
