package org.example.kihelp.wallet.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.model.resp.WalletResponse;
import org.example.kihelp.wallet.usecase.WalletGetUseCase;
import org.example.kihelp.wallet.usecase.WalletUpdateUseCase;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wallets")
@RequiredArgsConstructor
public class WalletController {
    private final WalletGetUseCase walletGetUseCase;
    private final WalletUpdateUseCase walletUpdateUseCase;

    @GetMapping("/wallet")
    public List<WalletResponse> getWallets(){
        return walletGetUseCase.getWallets();
    }

    @GetMapping("/wallet/by/{wallet_id}")
    public WalletResponse getWallet(@PathVariable("wallet_id") Integer walletId){
        return walletGetUseCase.getWallet(walletId);
    }

    @GetMapping("/wallet/user")
    public WalletResponse getWalletByUser(){
        return walletGetUseCase.getWalletByUser();
    }

    @PutMapping("/wallet/by/{wallet_id}")
    public void updateWallet(@PathVariable("wallet_id") Integer walletId,
                             @Valid @RequestBody WalletRequest walletRequest){
        walletUpdateUseCase.updateWallet(walletId, walletRequest);
    }

    @PutMapping("/wallet/by/user/{telegram_id}")
    public void updateWalletByUser(@PathVariable("telegram_id") String telegramId,
                                   @Valid @RequestBody WalletRequest walletRequest){
        walletUpdateUseCase.updateWalletByUser(telegramId, walletRequest);
    }
}
