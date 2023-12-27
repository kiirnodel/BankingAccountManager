package com.banking.boundary.strategy;

import com.banking.boundary.strategy.dto.TransferValidationDTO;

public interface TransferValidationStrategy {
    void validateTransfer(TransferValidationDTO validationDTO);
}
