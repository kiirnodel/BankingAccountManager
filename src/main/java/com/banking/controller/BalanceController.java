package com.banking.controller;

import com.banking.boundary.usecase.IBalanceUseCase;
import com.banking.boundary.usecase.dto.BalanceRequestDTO;
import com.banking.boundary.usecase.dto.BalanceResponseDTO;
import com.banking.boundary.usecase.dto.TransferRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/v1/balance")
@Tag(name = "api for Balance info")
@Slf4j
@RequiredArgsConstructor
public class BalanceController {

    private final IBalanceUseCase balanceService;

    @GetMapping
    @Operation(summary = "consult account balance")
    public CompletableFuture<ResponseEntity<BalanceResponseDTO>> getAccountBalance(@Valid @RequestBody BalanceRequestDTO requestDTO) {
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(balanceService.getAccountBalance(requestDTO), HttpStatus.OK));
    }
}