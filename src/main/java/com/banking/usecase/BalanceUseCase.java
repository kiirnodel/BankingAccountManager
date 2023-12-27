package com.banking.usecase;

import com.banking.boundary.repository.IAccountRepoService;
import com.banking.boundary.usecase.IBalanceUseCase;
import com.banking.boundary.usecase.dto.BalanceRequestDTO;
import com.banking.boundary.usecase.dto.BalanceResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BalanceUseCase implements IBalanceUseCase {
    private final IAccountRepoService accountService;
    @Override
    public BalanceResponseDTO getAccountBalance(BalanceRequestDTO requestDTO) {
        return BalanceResponseDTO.builder()
                .balance(accountService.getAccountInfo(requestDTO.clientAccount()).getBalance())
                .build();
    }
}
