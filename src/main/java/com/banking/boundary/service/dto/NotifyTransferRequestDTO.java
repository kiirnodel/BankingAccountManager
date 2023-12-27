package com.banking.boundary.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Builder
public class NotifyTransferRequestDTO {
    Long clientBank;
    Long clientAgency;
    Long clientAccount;
    Long transferBank;
    Long transferAgency;
    Long transferAccount;
    Double transferValue;
}
