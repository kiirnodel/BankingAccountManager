package com.banking.usecase;

import com.banking.boundary.messaging.IQueuePublishService;
import com.banking.boundary.repository.IAccountRepoService;
import com.banking.boundary.repository.dto.TransferAcc2AccRequestDTO;
import com.banking.boundary.service.IClientService;
import com.banking.boundary.service.dto.ClientInfoRequestDTO;
import com.banking.boundary.service.dto.ClientInfoResponseDTO;
import com.banking.boundary.service.dto.NotifyTransferRequestDTO;
import com.banking.boundary.strategy.TransferValidationStrategy;
import com.banking.boundary.strategy.dto.TransferValidationDTO;
import com.banking.boundary.usecase.ITransferUseCase;
import com.banking.boundary.usecase.dto.ReturnDTO;
import com.banking.boundary.usecase.dto.TransferRequestDTO;
import com.banking.entities.Account;
import com.banking.enuns.BusinessType;
import com.banking.enuns.MessageType;
import com.banking.enuns.ValidationType;
import com.banking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Service
@Slf4j
@RequiredArgsConstructor
public class TransferUseCase implements ITransferUseCase {

    private final IClientService clientService;
    private final IAccountRepoService accountService;
    private final Map<String, TransferValidationStrategy> validationStrategy;
    private final IQueuePublishService queuePublishService;

    @Override
    public ReturnDTO transferBetweenAccounts(TransferRequestDTO requestDTO) {
        try {
            /* Pega Informações do Cliente(nome etc) */
            CompletableFuture<ClientInfoResponseDTO> clientInfo = CompletableFuture.supplyAsync( () -> clientService
                    .getClientName(ClientInfoRequestDTO.builder().clientId(requestDTO.clientId()).build())
                    .orElseThrow());

            /* Pega informações da conta */
            CompletableFuture<Account> accountInfo = CompletableFuture.supplyAsync(() ->
                    accountService.getAccountInfo(requestDTO.clientAccount()));

            CompletableFuture<Account> transferAccountInfo = CompletableFuture.supplyAsync(() ->
                    accountService.getAccountInfo(requestDTO.transferAccount()));

            /* Faz as devidas validações */
            Arrays.stream(ValidationType.values())
                    .parallel().forEach(validationType ->
                        validationStrategy.get(validationType.value())
                                .validateTransfer(new TransferValidationDTO(clientInfo, accountInfo, requestDTO)));

            /* Realiza transferência */
            accountService.transferAcc2Acc(new TransferAcc2AccRequestDTO(
                    accountInfo.join(),
                    transferAccountInfo.join(),
                    requestDTO.transferValue()));

            /* Postar mensagem para notificar BACEN */
            CompletableFuture.runAsync(() ->
                    queuePublishService.publish(
                            NotifyTransferRequestDTO.builder()
                                    .clientBank(requestDTO.clientBank())
                                    .clientAgency(requestDTO.clientAgency())
                                    .clientAccount(requestDTO.clientAccount())
                                    .transferBank(requestDTO.transferBank())
                                    .transferAgency(requestDTO.transferAgency())
                                    .transferAccount(requestDTO.transferAccount()).build()
                    )
            );

            /* Retornar sucesso */
            return BusinessType.TRANSFER_SUCCESSFUL.returnDTO();
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (Exception e) {
            log.error(MessageType.GENERIC.value(), e);
            throw new BusinessException(BusinessType.LIMIT_EXCEEDED);
        }
    }
}
