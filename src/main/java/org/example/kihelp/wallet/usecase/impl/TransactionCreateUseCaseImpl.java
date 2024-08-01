package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.mapper.TransactionRequestToTransactionMapper;
import org.example.kihelp.wallet.model.req.TransactionRequest;
import org.example.kihelp.wallet.service.TransactionService;
import org.example.kihelp.wallet.usecase.TransactionCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionCreateUseCaseImpl implements TransactionCreateUseCase {
    private final TransactionRequestToTransactionMapper transactionRequestToTransactionMapper;
    private final TransactionService transactionService;

    @Override
    public void createTransaction(TransactionRequest request) {
        var transaction = transactionRequestToTransactionMapper.map(request);

        transactionService.createTransaction(transaction);
    }
}
