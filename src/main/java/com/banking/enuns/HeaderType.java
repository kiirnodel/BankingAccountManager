package com.banking.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum HeaderType {
    CLIENT_ID("clientId");
    private final String value;
}
