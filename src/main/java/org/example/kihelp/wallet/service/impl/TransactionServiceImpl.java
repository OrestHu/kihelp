package org.example.kihelp.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.exception.TransactionAlreadyExistException;
import org.example.kihelp.wallet.exception.TransactionNotFoundException;
import org.example.kihelp.wallet.model.Transaction;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.respository.TransactionRepository;
import org.example.kihelp.wallet.service.TransactionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.example.kihelp.wallet.utils.MessageError.TRANSACTION_ALREADY_EXISTS;
import static org.example.kihelp.wallet.utils.MessageError.TRANSACTION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;

    @Override
    public void createTransaction(Transaction transaction) {
        var exist = transactionRepository.existsByPaymentId(transaction.getPaymentId());

        if (exist) {
            throw new TransactionAlreadyExistException(TRANSACTION_ALREADY_EXISTS);
        }

        transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Transaction> getTransactionsByWallet(Wallet wallet, Pageable pageable) {
        return transactionRepository.findAllByWallet(wallet, pageable);
    }

    @Override
    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction getTransaction(Long transactionId) {
        return transactionRepository.findById(transactionId)
                .orElseThrow(() -> new TransactionNotFoundException(TRANSACTION_NOT_FOUND));
    }

    @Override
    public Double getAnIncome() {
        return transactionRepository.findTotalAmount();
    }

    @Override
    public void deleteTransaction(Long transactionId) {
        var transaction = getTransaction(transactionId);

        transactionRepository.delete(transaction);
    }
}
