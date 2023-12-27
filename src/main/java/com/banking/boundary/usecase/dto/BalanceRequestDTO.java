package com.banking.boundary.usecase.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record BalanceRequestDTO(
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientId,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientBank,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientAgency,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientAccount
) {

}
