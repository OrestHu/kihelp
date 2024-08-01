package org.example.kihelp.wallet.mapper.impl;

import org.example.kihelp.wallet.mapper.TransactionToTransactionResponseMapper;
import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.resp.TransactionResponse;
import org.springframework.stereotype.Component;

@Component
public class TransactionToTransactionResponseMapperImpl implements TransactionToTransactionResponseMapper {

    @Override
    public TransactionResponse map(Transaction transaction) {
        return new TransactionResponse(
                transaction.getId(),
                transaction.getInitial(),
                transaction.getAmount(),
                transaction.getCreatedTimeStamp().toString()
        );
    }
}
