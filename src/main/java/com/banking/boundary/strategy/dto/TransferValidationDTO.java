package com.banking.boundary.strategy.dto;

import com.banking.boundary.service.dto.ClientInfoResponseDTO;
import com.banking.boundary.usecase.dto.TransferRequestDTO;
import com.banking.entities.Account;

import java.util.concurrent.CompletableFuture;

public record TransferValidationDTO(
        CompletableFuture<ClientInfoResponseDTO> clientInfo,
        CompletableFuture<Account> accountInfo,
        TransferRequestDTO requestDTO
) {
}
