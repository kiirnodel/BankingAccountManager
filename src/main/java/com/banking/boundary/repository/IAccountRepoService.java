package com.banking.boundary.repository;

import com.banking.boundary.repository.dto.TransferAcc2AccRequestDTO;
import com.banking.entities.Account;

public interface IAccountRepoService {
    Account getAccountInfo(Long accountId);

    void transferAcc2Acc(TransferAcc2AccRequestDTO requestDTO);
}
