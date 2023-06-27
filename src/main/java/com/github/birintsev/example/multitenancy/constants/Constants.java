package com.github.birintsev.example.multitenancy.constants;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {

    public static final String HEADER_TENANT = "X-Tenant-Name";
}
