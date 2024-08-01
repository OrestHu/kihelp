package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.service.TransactionService;
import org.example.kihelp.wallet.usecase.TransactionDeleteUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionDeleteUseCaseImpl implements TransactionDeleteUseCase {
    private final TransactionService transactionService;

    @Override
    public void deleteTransaction(Long transactionId) {
        transactionService.deleteTransaction(transactionId);
    }
}
