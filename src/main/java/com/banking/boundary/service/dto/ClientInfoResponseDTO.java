package com.banking.boundary.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Builder
public class ClientInfoResponseDTO {
    String clientName;
    Long clientId;
    Long clientDocument;
    String clientGender;
}
