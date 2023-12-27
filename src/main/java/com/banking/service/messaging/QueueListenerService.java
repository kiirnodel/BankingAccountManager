package com.banking.service.messaging;

import com.banking.boundary.messaging.IQueueListenerService;
import com.banking.boundary.messaging.IQueuePublishService;
import com.banking.boundary.service.IBacenService;
import com.banking.boundary.service.dto.NotifyTransferRequestDTO;
import com.banking.enuns.MessagingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueueListenerService implements IQueueListenerService {

    private final IBacenService bacenService;
    private final CountDownLatch latch = new CountDownLatch(1);

    @Override
    public void process(NotifyTransferRequestDTO requestDTO) {
       bacenService.notifyTransfer(requestDTO);
       latch.countDown();
    }
}
