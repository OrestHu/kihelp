package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.WalletUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletUpdateUseCaseImpl implements WalletUpdateUseCase {
    private final WalletService walletService;

    @Override
    public void updateWallet(Integer walletId, WalletRequest walletRequest) {
        walletService.updateWallet(walletId, walletRequest);
    }
}
