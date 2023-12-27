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
import static com.banking.enuns.CacheType.CacheConst.SESSION_DURATION;
import static com.banking.enuns.CacheType.CacheConst.TRANSFER_INFO_NAME;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum CacheType {
    CLIENT_INFO(CLIENT_INFO_NAME, 10000, 1000000, SESSION_DURATION),
    TRANSFER_INFO(TRANSFER_INFO_NAME, 10000, 1000000, END_OF_DAY);

    private final String description;
    private final int capacity;
    private final int size;
    private final Duration duration;

    @UtilityClass
    public static class CacheConst {
        public static final String CLIENT_INFO_NAME = "clientInfoCache";
        public static final String TRANSFER_INFO_NAME = "transferInfoCache";
        public static final Duration END_OF_DAY = Duration.between(LocalDateTime.now(), LocalDate.now().atTime(LocalTime.MAX));
        public static final Duration SESSION_DURATION = Duration.ofMinutes(30);
    }
}
