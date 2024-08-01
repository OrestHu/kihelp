package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.mapper.TransactionToTransactionResponseMapper;
import org.example.kihelp.wallet.model.req.TransactionFindRequest;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.model.resp.EarningResponse;
import org.example.kihelp.wallet.model.resp.TransactionResponse;
import org.example.kihelp.wallet.service.TransactionService;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.TransactionGetUseCase;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TransactionGetUseCaseImpl implements TransactionGetUseCase {
    private final TransactionService transactionService;
    private final WalletService walletService;
    private final TransactionToTransactionResponseMapper transactionToTransactionResponseMapper;

    @Override
    public List<TransactionResponse> getTransactionsByWallet(Integer walletId, TransactionFindRequest findRequest) {
        var sort = Sort.by(Sort.Direction.DESC, "createdTimeStamp");
        var pageRequest = PageRequest.of(findRequest.page(), findRequest.limit(), sort);

        var wallet = walletService.getWallet(walletId);
        var transactions = transactionService.getTransactionsByWallet(wallet, pageRequest);

        return transactions.stream().map(transactionToTransactionResponseMapper::map).toList();
    }

    @Override
    public List<TransactionResponse> getTransactions() {
        var transactions = transactionService.getTransactions();

        return transactions.stream().map(transactionToTransactionResponseMapper::map).toList();
    }

    @Override
    public EarningResponse getAnIncome() {
        var income = transactionService.getAnIncome();

        return new EarningResponse(income);
    }
}
