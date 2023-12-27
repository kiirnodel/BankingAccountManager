package com.banking.boundary.usecase.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record TransferRequestDTO(
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientId,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientBank,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long clientAgency,
        @Positive @Digits(integer = 10000, fraction = 0) long clientAccount,
        @Positive @Digits(integer = 10000, fraction = 2) double transferValue,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long transferBank,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long transferAgency,
        @NotNull @Positive @Digits(integer = 10000, fraction = 0) long transferAccount
) {

}
