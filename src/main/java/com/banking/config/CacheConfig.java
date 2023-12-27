package com.banking.config;

import com.banking.enuns.CacheType;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.Temporal;
import java.util.Calendar;

@EnableAsync
@EnableCaching
@Configuration
@RequiredArgsConstructor
@Slf4j
public class CacheConfig {

    @Bean
    public CacheManager clientCache() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheType.CLIENT_INFO.description());
        cacheManager.setCaffeine(clientInfoCacheBuilder());
        cacheManager.setAsyncCacheMode(true);
        return cacheManager;
    }

    @Bean
    @Primary
    public CacheManager transferCache() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager(CacheType.TRANSFER_INFO.description());
        cacheManager.setCaffeine(transferCacheBuilder());
        cacheManager.setAsyncCacheMode(true);
        return cacheManager;
    }

    Caffeine<Object, Object> clientInfoCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(CacheType.CLIENT_INFO.capacity())
                .maximumSize(CacheType.CLIENT_INFO.size())
                .expireAfterWrite(CacheType.CLIENT_INFO.duration());
    }

    Caffeine<Object, Object> transferCacheBuilder() {
        return Caffeine.newBuilder()
                .initialCapacity(CacheType.TRANSFER_INFO.capacity())
                .maximumSize(CacheType.TRANSFER_INFO.size())
                .expireAfterWrite(CacheType.TRANSFER_INFO.duration());
    }
}
