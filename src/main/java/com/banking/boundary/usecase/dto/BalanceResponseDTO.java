package com.banking.boundary.usecase.dto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BalanceResponseDTO {
    @NotNull @Digits(integer = 10000, fraction = 2) double balance;
}
