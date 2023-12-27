package com.banking.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum MessagingType {
    BACEN_NOTIFY("notifyBacen", "process_event", "bacenOutput", "bacenInput");

    private final String header;
    private final String eventDescription;
    private final String producer;
    private final String consumer;
}
