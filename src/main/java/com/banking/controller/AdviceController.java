package com.banking.controller;

import com.banking.boundary.usecase.dto.ReturnDTO;
import com.banking.enuns.BusinessType;
import com.banking.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import com.banking.enuns.MessageType;
import com.banking.enuns.StringType;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@ControllerAdvice
@Slf4j
public class AdviceController {

    @ExceptionHandler(value = {WebExchangeBindException.class})
    protected ResponseEntity<Object> handleControllerErrorValidation(WebExchangeBindException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .parallelStream()
                .map(ex -> ex.getField()
                        .concat(StringType.BLANK.value())
                        .concat(Objects.requireNonNull(ex.getDefaultMessage())))
                .toList();

        ReturnDTO response = ReturnDTO.builder()
                .errors(errors)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .message(MessageType.REQUEST_VALIDATION.value())
                .build();

        log.error(response.getErrors().toString());

        return new ResponseEntity<>(response,response.getHttpStatus());
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    protected ResponseEntity<Object> handleDTOErrorValidation(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .parallelStream()
                .map(ex -> ex.getField()
                        .concat(StringType.BLANK.value())
                        .concat(Objects.requireNonNull(ex.getDefaultMessage())))
                .toList();

        ReturnDTO response = ReturnDTO.builder()
                .errors(errors)
                .httpStatus(HttpStatus.NOT_ACCEPTABLE)
                .message(MessageType.GENERIC.value())
                .build();

        return new ResponseEntity<>(response,response.getHttpStatus());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<Object> handleGlobalErrors(Exception e) {
        List<String> errors = Collections.singletonList(e.getMessage());

        ReturnDTO response = ReturnDTO.builder()
                .errors(errors)
                .httpStatus(HttpStatus.CONFLICT)
                .message(MessageType.GENERIC.value())
                .build();

        return new ResponseEntity<>(response,response.getHttpStatus());
    }

    @ExceptionHandler(value = {BusinessException.class})
    protected ResponseEntity<Object> handleBusinessErrors(BusinessException e) {
        ReturnDTO errorType = BusinessType.valueOf(e.getMessage()).returnDTO();

        return new ResponseEntity<>(errorType, errorType.getHttpStatus());
    }
}