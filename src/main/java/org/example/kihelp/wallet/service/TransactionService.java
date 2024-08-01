package org.example.kihelp.wallet.service;

import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TransactionService {
    void createTransaction(Transaction transaction);
    Page<Transaction> getTransactionsByWallet(Wallet wallet, Pageable pageable);
    List<Transaction> getTransactions();
    Transaction getTransaction(Long transactionId);
    Double getAnIncome();
    void deleteTransaction(Long transactionId);
}
