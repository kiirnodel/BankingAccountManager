package com.banking.boundary.messaging;

import com.banking.boundary.service.dto.NotifyTransferRequestDTO;

public interface IQueuePublishService {
    void publish(NotifyTransferRequestDTO message);
}
