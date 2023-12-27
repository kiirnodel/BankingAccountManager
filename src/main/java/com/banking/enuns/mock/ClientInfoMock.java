package com.banking.enuns.mock;

import com.banking.boundary.service.dto.ClientInfoResponseDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.UtilityClass;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static com.banking.enuns.CacheType.CacheConst.CLIENT_INFO_NAME;
import static com.banking.enuns.CacheType.CacheConst.END_OF_DAY;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum ClientInfoMock {
    FIRST(1L, ClientInfoResponseDTO.builder()
            .clientId(1L)
            .clientDocument(12345678910L)
            .clientGender("Male")
            .clientName("Victor").build()),
    SECOND(2L, ClientInfoResponseDTO.builder()
            .clientId(1L)
            .clientDocument(12345678911L)
            .clientGender("Female")
            .clientName("Thai").build()),
    THIRD(3L, ClientInfoResponseDTO.builder()
            .clientId(1L)
            .clientDocument(12345678912L)
            .clientGender("Other")
            .clientName("Jackie").build());

    private final long clientId;
    private final ClientInfoResponseDTO value;

    public static ClientInfoMock getById(Long clientId) {
        return Arrays.stream(ClientInfoMock.values()).toList().stream()
                .filter(it -> it.clientId == clientId)
                .findFirst()
                .orElseThrow();
    }
}
