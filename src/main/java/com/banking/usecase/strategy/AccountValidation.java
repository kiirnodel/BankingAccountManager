package com.banking.usecase.strategy;

import com.banking.boundary.strategy.dto.TransferValidationDTO;
import com.banking.entities.Account;
import com.banking.enuns.BusinessType;
import com.banking.enuns.ValidationType;
import com.banking.boundary.strategy.TransferValidationStrategy;
import com.banking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


@Service(ValidationType.ValidationConst.ACCOUNT_NAME)
@Slf4j
@RequiredArgsConstructor
public class AccountValidation implements TransferValidationStrategy {

    @Override
    public void validateTransfer(TransferValidationDTO validationDTO) {
        try {
            Account account = validationDTO.accountInfo().join();
            validateAccount(account);
            validateValue(account,validationDTO);
        } catch (Exception e) {
            throw new BusinessException(BusinessType.ACCOUNT_GENERIC);
        }
    }

    private void validateValue(Account account, TransferValidationDTO validationDTO) {
       if (validationDTO.requestDTO().transferValue() >= account.getBalance()) {
           throw new BusinessException(BusinessType.LIMIT_EXCEEDED);
       }
    }

    private void validateAccount(Account account) {
        if (!account.isActive()) {
            throw new BusinessException(BusinessType.ACCOUNT_NOT_ACTIVE);
        }
    }
}
