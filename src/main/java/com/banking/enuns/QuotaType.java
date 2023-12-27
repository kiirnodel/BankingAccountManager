package com.banking.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum QuotaType {
    DAILY_TRANSFER(1000.00);
    private final double value;
}
