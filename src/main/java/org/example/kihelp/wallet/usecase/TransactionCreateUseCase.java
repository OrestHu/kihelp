package org.example.kihelp.wallet.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.wallet.model.req.TransactionRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface TransactionCreateUseCase {
    void createTransaction(@Valid TransactionRequest request);
}
