package org.example.kihelp.wallet.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.wallet.model.req.TransactionFindRequest;
import org.example.kihelp.wallet.model.resp.EarningResponse;
import org.example.kihelp.wallet.model.resp.TransactionResponse;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface TransactionGetUseCase {
    List<TransactionResponse> getTransactionsByWallet(Integer walletId, @Valid TransactionFindRequest findRequest);
    List<TransactionResponse> getTransactions();
    EarningResponse getAnIncome();
}
