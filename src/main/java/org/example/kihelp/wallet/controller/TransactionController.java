package org.example.kihelp.wallet.controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.model.req.TransactionFindRequest;
import org.example.kihelp.wallet.model.req.TransactionRequest;
import org.example.kihelp.wallet.model.resp.EarningResponse;
import org.example.kihelp.wallet.model.resp.TransactionResponse;
import org.example.kihelp.wallet.usecase.TransactionCreateUseCase;
import org.example.kihelp.wallet.usecase.TransactionDeleteUseCase;
import org.example.kihelp.wallet.usecase.TransactionGetUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionCreateUseCase transactionCreateUseCase;
    private final TransactionGetUseCase transactionGetUseCase;
    private final TransactionDeleteUseCase transactionDeleteUseCase;

    @PostMapping("/transaction")
    public void createTransaction(@Valid @RequestBody TransactionRequest transactionRequest){
        transactionCreateUseCase.createTransaction(transactionRequest);
    }

    @GetMapping("/transaction/{wallet_id}")
    public List<TransactionResponse> getTransactionsByWallet(
            @PathVariable("wallet_id") Integer walletId,
            @PathParam("page") int page,
            @PathParam("limit") int limit
    ){
        var transactionFindRequest = new TransactionFindRequest(page, limit);
        return transactionGetUseCase.getTransactionsByWallet(walletId, transactionFindRequest);
    }

    @GetMapping("/transaction/all")
    public List<TransactionResponse> getTransactions(){
        return transactionGetUseCase.getTransactions();
    }

    @GetMapping("/earnings")
    public EarningResponse getAnIncome(){
        return transactionGetUseCase.getAnIncome();
    }

    @DeleteMapping("/transaction/{transaction_id}")
    public void deleteTransaction(@PathVariable("transaction_id") Long transactionId){
        transactionDeleteUseCase.deleteTransaction(transactionId);
    }
}
