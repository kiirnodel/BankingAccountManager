package com.banking.boundary.messaging;

import com.banking.boundary.service.dto.NotifyTransferRequestDTO;

public interface IQueueListenerService {
    void process(NotifyTransferRequestDTO requestDTO);
}
