package com.banking.enuns;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent = true)
@RequiredArgsConstructor
public enum MessageType {
    REQUEST_VALIDATION("Infelizmente sua solicitação não pode ser processada, se o problema persistir, entre em contato conosco!"),
    GENERIC("Sinto muito, houve um erro inesperado, entre em contato conosco!"),
    LIMIT_EXCEEDED("Ops, você já excedeu o seu limite diário de transferência!"),
    ACCOUNT_NOT_ACTIVE("Ops, sua conta encontra-se inativa no momento, por favor, entre em contato conosco!"),
    ACCOUNT_NOT_VALID("Ops, a conta para transferência não existe, por favor, valida as informações e tente novamente!"),
    ACCOUNT_GENERIC("Ops, houve um problema ao validar os dados da sua conta, caso o erro persista, entre em contato conosco!"),
    TRANSFER_GENERIC("Ops, houve um problema ao validar os dados da transferência, caso o erro persista, entre em contato conosco!"),
    TRANSFER_SUCCESS("Transferência realizada com sucesso!"),
    URI_BUILD("Ops, sistema indisponível por alguns instantes, tente novamente mais tarde!");

    private final String value;
}
