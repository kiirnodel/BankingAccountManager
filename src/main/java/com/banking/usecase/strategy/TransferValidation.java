package com.banking.usecase.strategy;
import com.banking.boundary.strategy.dto.TransferValidationDTO;
import com.banking.enuns.BusinessType;
import com.banking.enuns.CacheType;
import com.banking.enuns.QuotaType;
import com.banking.enuns.ValidationType;
import com.banking.exception.BusinessException;
import com.banking.boundary.strategy.TransferValidationStrategy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service(ValidationType.ValidationConst.TRANSFER_NAME)
@Slf4j
@RequiredArgsConstructor
public class TransferValidation implements TransferValidationStrategy {

    private final CacheManager cacheManager;

    @Override
    public void validateTransfer(TransferValidationDTO validationDTO) {
        try {
            Cache transferCache = cacheManager.getCache(CacheType.TRANSFER_INFO.description());
            validateDailyQuota(validationDTO, transferCache);
        } catch (Exception e) {
            throw new BusinessException(BusinessType.TRANSFER_GENERIC);
        }
    }

    private void validateDailyQuota(TransferValidationDTO validationDTO, Cache transferCache) {
        double totalTransfer = validationDTO.requestDTO().transferValue();
        if (transferCache.get(validationDTO.requestDTO().clientAccount()) != null) {
            totalTransfer = totalTransfer + Double.parseDouble(Objects.requireNonNull(Objects.requireNonNull(transferCache.get(validationDTO.requestDTO().clientAccount())).get()).toString());
        }
        if (totalTransfer > QuotaType.DAILY_TRANSFER.value()) {
            throw new BusinessException(BusinessType.LIMIT_EXCEEDED);
        }
    }
}
