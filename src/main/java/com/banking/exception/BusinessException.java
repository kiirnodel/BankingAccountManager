package com.banking.exception;

import com.banking.enuns.BusinessType;

public class BusinessException extends RuntimeException{
    public BusinessException(BusinessType error){
        super(error.name());
    }
}
