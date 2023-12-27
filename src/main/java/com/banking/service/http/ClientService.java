package com.banking.service.http;

import com.banking.boundary.service.IClientService;
import com.banking.boundary.service.dto.ClientInfoRequestDTO;
import com.banking.boundary.service.dto.ClientInfoResponseDTO;
import com.banking.enuns.BusinessType;
import com.banking.enuns.CacheType;
import com.banking.enuns.HeaderType;
import com.banking.enuns.mock.ClientInfoMock;
import com.banking.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final WebClient webClient;

    @Value("${client-service.get-info}")
    private String getClientInfoUrl;

    @Override
    @Retryable(retryFor = {HttpClientErrorException.class, HttpServerErrorException.class}, maxAttempts = 5, backoff = @Backoff(delay = 100))
    @Cacheable(CacheType.CacheConst.CLIENT_INFO_NAME)
    public Optional<ClientInfoResponseDTO> getClientName(ClientInfoRequestDTO requestDTO) {
        try {
            URI uri = new URI(getClientInfoUrl);
            return  webClient.get()
                    .uri(uri)
                    .header(HeaderType.CLIENT_ID.value(), requestDTO.clientId().toString())
                    .retrieve()
                    .bodyToMono(ClientInfoResponseDTO.class)
                    .blockOptional();
        } catch (URISyntaxException e) {
            throw new BusinessException(BusinessType.URI_BUILD);
        } catch (Exception e) {
            return Optional.of(ClientInfoMock.getById(requestDTO.clientId()).value());
        }
    }
}
