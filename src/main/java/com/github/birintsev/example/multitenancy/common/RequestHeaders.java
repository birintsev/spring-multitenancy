package com.github.birintsev.example.multitenancy.common;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;

import static com.github.birintsev.example.multitenancy.constants.Constants.HEADER_TENANT;

@Component
@RequestScope
@RequiredArgsConstructor
public class RequestHeaders {

    private final HttpServletRequest httpServletRequest;

    public String getTenantName() {
        return httpServletRequest.getHeader(HEADER_TENANT);
    }
}
