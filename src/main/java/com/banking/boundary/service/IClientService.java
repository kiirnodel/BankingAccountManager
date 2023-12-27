package com.banking.boundary.service;

import com.banking.boundary.service.dto.ClientInfoRequestDTO;
import com.banking.boundary.service.dto.ClientInfoResponseDTO;

import java.util.Optional;

public interface IClientService {
    Optional<ClientInfoResponseDTO> getClientName(ClientInfoRequestDTO requestDTO);
}
