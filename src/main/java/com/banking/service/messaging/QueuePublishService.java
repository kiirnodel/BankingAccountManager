package com.banking.service.messaging;

import com.banking.boundary.messaging.IQueuePublishService;
import com.banking.boundary.service.dto.NotifyTransferRequestDTO;
import com.banking.enuns.MessagingType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class QueuePublishService implements IQueuePublishService {
    private final StreamBridge streamBridge;

    @Override
    public void publish(NotifyTransferRequestDTO message) {
        Message<NotifyTransferRequestDTO> sendMessage =
                MessageBuilder.withPayload(message)
                        .setHeaderIfAbsent(
                                MessagingType.BACEN_NOTIFY.header(),
                                MessagingType.BACEN_NOTIFY.eventDescription())
                        .build();
        streamBridge.send(MessagingType.BACEN_NOTIFY.producer(), sendMessage);
    }
}
