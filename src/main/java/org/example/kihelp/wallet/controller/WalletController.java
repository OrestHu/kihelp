package org.example.kihelp.wallet.controller;

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

    @GetMapping("/wallet/{wallet_id}")
    public WalletResponse getWallet(@PathVariable("wallet_id") Integer walletId){
        return walletGetUseCase.getWallet(walletId);
    }

    @GetMapping("/wallet/user/{user_id}")
    public WalletResponse getWalletByUser(@PathVariable("user_id") Long userId){
        return walletGetUseCase.getWalletByUser(userId);
    }

    @PutMapping("/wallet/{wallet_id}")
    public void updateWallet(@PathVariable("wallet_id") Integer walletId,
                             @RequestBody WalletRequest walletRequest){
        walletUpdateUseCase.updateWallet(walletId, walletRequest);
    }
}
