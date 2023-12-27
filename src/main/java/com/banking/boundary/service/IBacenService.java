package com.banking.boundary.service;

import com.banking.boundary.service.dto.NotifyTransferRequestDTO;

public interface IBacenService {
    void notifyTransfer(NotifyTransferRequestDTO requestDTO);
}
