package org.example.kihelp.wallet.mapper;

import org.example.kihelp.subject.mapper.Mapper;
import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.resp.TransactionResponse;

public interface TransactionToTransactionResponseMapper extends Mapper<TransactionResponse, Transaction> {
}
