package com.banking.boundary.usecase;

import com.banking.boundary.usecase.dto.BalanceRequestDTO;
import com.banking.boundary.usecase.dto.BalanceResponseDTO;

public interface IBalanceUseCase {
    BalanceResponseDTO getAccountBalance(BalanceRequestDTO requestDTO);
}
