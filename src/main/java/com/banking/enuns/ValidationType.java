package com.banking.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static com.banking.enuns.CacheType.CacheConst.CLIENT_INFO_NAME;
import static com.banking.enuns.CacheType.CacheConst.END_OF_DAY;
import static com.banking.enuns.ValidationType.ValidationConst.ACCOUNT_NAME;
import static com.banking.enuns.ValidationType.ValidationConst.TRANSFER_NAME;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum ValidationType {
    ACCOUNT(ACCOUNT_NAME),
    TRANSFER(TRANSFER_NAME);;

    private final String value;
    @UtilityClass
    public static class ValidationConst {
        public static final String ACCOUNT_NAME = "account";
        public static final String TRANSFER_NAME = "transfer";
    }
}
