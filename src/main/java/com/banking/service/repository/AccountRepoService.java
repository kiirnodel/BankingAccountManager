package com.banking.service.repository;

import com.banking.boundary.repository.IAccountRepoService;
import com.banking.boundary.repository.dto.TransferAcc2AccRequestDTO;
import com.banking.entities.Account;
import com.banking.enuns.CacheType;
import com.banking.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AccountRepoService implements IAccountRepoService {

    private final AccountRepository repository;
    private final CacheManager cacheManager;

    @Override
    @Transactional(readOnly = true)
    public Account getAccountInfo(Long accountId) {
            return repository.getReferenceById(accountId);
    }

    @Override
    @Transactional
    public void transferAcc2Acc(TransferAcc2AccRequestDTO requestDTO) {
            requestDTO.transferAccountInfo().setBalance(requestDTO.transferAccountInfo().getBalance() + requestDTO.transferValue());
            requestDTO.clientAccountInfo().setBalance(requestDTO.transferAccountInfo().getBalance() - requestDTO.transferValue());
            repository.saveAllAndFlush(List.of(requestDTO.transferAccountInfo(), requestDTO.clientAccountInfo()));
            saveTransferValue(requestDTO.clientAccountInfo().getAccountId(), requestDTO.transferValue());
    }

    private void saveTransferValue(long accountId, double transferAmount) {
        Objects.requireNonNull(cacheManager.getCache(CacheType.TRANSFER_INFO.description())).put(accountId, transferAmount);
    }
}
