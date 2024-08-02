package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.mapper.TransactionRequestToTransactionMapper;
import org.example.kihelp.wallet.model.req.TransactionRequest;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.TransactionService;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.TransactionCreateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TransactionCreateUseCaseImpl implements TransactionCreateUseCase {
    private final TransactionRequestToTransactionMapper transactionRequestToTransactionMapper;
    private final TransactionService transactionService;
    private final UserApiService userApiService;
    private final WalletService walletService;

    @Override
    public void createTransaction(TransactionRequest request) {
        var transaction = transactionRequestToTransactionMapper.map(request);
        var user = userApiService.currentUserAccountByTelegramId(request.telegramId());

        transactionService.createTransaction(transaction);
        walletService.topUpWallet(user.userId(), new WalletRequest(request.amount()));
    }
}
