package com.banking.boundary.usecase.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
public class ReturnDTO {
    HttpStatus httpStatus;
    List<String> errors;
    String message;
}
