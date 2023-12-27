package com.banking.service.http;

import com.banking.boundary.service.IBacenService;
import com.banking.boundary.service.dto.NotifyTransferRequestDTO;
import com.banking.enuns.BusinessType;
import com.banking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
@Service
@RequiredArgsConstructor
public class BacenService implements IBacenService {

    private final WebClient webClient;

    @Value("${bacen-service.notify-transfer}")
    private String notifyTransferUrl;

    @Override
    @Retryable(retryFor = {HttpClientErrorException.class, HttpServerErrorException.class}, maxAttempts = 5, backoff = @Backoff(delay = 3500))
    public void notifyTransfer(NotifyTransferRequestDTO requestDTO) {
        try {
            URI uri = new URI(notifyTransferUrl);
            webClient.post()
                    .uri(uri)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(requestDTO)
                    .retrieve()
                    .bodyToMono(Object.class)
                    .block();
        } catch (URISyntaxException e) {
            throw new BusinessException(BusinessType.URI_BUILD);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}
