package com.banking.boundary.usecase;

import com.banking.boundary.usecase.dto.ReturnDTO;
import com.banking.boundary.usecase.dto.TransferRequestDTO;


public interface ITransferUseCase {
    ReturnDTO transferBetweenAccounts(TransferRequestDTO requestDTO);
}
