package com.banking.boundary.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(fluent = true)
@Builder
public class ClientInfoRequestDTO {
    Long clientId;
}
