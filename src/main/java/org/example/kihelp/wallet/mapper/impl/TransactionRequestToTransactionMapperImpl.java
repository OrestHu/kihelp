package org.example.kihelp.wallet.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.mapper.TransactionRequestToTransactionMapper;
import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.req.TransactionRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class TransactionRequestToTransactionMapperImpl implements TransactionRequestToTransactionMapper {
    private final WalletService walletService;
    private final UserApiService userApiService;

    @Override
    public Transaction map(TransactionRequest transactionRequest) {
        Transaction transaction = new Transaction();

        transaction.setPaymentId(transactionRequest.paymentId());
        transaction.setInitial(transactionRequest.initial());
        transaction.setAmount(transactionRequest.amount());
        transaction.setCreatedTimeStamp(Instant.now());

        var user = userApiService.currentUserAccountByTelegramId(transactionRequest.telegramId());
        var wallet = walletService.getWalletByUser(user.userId());

        transaction.setWallet(wallet);

        return transaction;
    }
}
