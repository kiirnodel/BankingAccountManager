package com.banking.enuns;

import com.banking.boundary.usecase.dto.ReturnDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum BusinessType {
    LIMIT_EXCEEDED(ReturnDTO.builder()
            .message(MessageType.LIMIT_EXCEEDED.value())
            .httpStatus(HttpStatus.BAD_REQUEST).build()),
    ACCOUNT_NOT_ACTIVE(ReturnDTO.builder()
            .message(MessageType.ACCOUNT_NOT_ACTIVE.value())
            .httpStatus(HttpStatus.BAD_REQUEST).build()),
    ACCOUNT_GENERIC(ReturnDTO.builder()
            .message(MessageType.ACCOUNT_GENERIC.value())
            .httpStatus(HttpStatus.BAD_REQUEST).build()),
    TRANSFER_GENERIC(ReturnDTO.builder()
            .message(MessageType.LIMIT_EXCEEDED.value())
            .httpStatus(HttpStatus.BAD_REQUEST).build()),
    ACCOUNT_NOT_VALID(ReturnDTO.builder()
            .message(MessageType.ACCOUNT_NOT_VALID.value())
            .httpStatus(HttpStatus.BAD_REQUEST).build()),
    TRANSFER_SUCCESSFUL(ReturnDTO.builder()
            .message(MessageType.TRANSFER_SUCCESS.value())
            .httpStatus(HttpStatus.CREATED).build()),
    URI_BUILD(ReturnDTO.builder()
            .message(MessageType.URI_BUILD.value())
            .httpStatus(HttpStatus.CONFLICT).build());

    private final ReturnDTO returnDTO;
}
