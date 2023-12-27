package com.banking.controller;

import com.banking.boundary.usecase.ITransferUseCase;
import com.banking.boundary.usecase.dto.ReturnDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.banking.boundary.usecase.dto.TransferRequestDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/v1/transfer")
@Tag(name = "api to transfer money")
@Slf4j
@RequiredArgsConstructor
public class TransferController {

    private final AtomicLong counter = new AtomicLong();
    private final ITransferUseCase transferService;

    @PostMapping
    @Operation(summary = "transfer money between accounts")
    public CompletableFuture<ResponseEntity<ReturnDTO>> transferBetweenAccounts(@Valid @RequestBody TransferRequestDTO requestDTO) {
        log.info("Running request number {} running in {}", counter.incrementAndGet(), Thread.currentThread().getThreadGroup().getName());
        return CompletableFuture.supplyAsync(() -> new ResponseEntity<>(transferService.transferBetweenAccounts(requestDTO), HttpStatus.CREATED));
    }
}