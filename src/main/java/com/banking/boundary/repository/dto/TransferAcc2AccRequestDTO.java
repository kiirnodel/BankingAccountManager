package com.banking.boundary.repository.dto;

import com.banking.entities.Account;

public record TransferAcc2AccRequestDTO(
        Account clientAccountInfo,
        Account transferAccountInfo,
        double transferValue
) {
}
