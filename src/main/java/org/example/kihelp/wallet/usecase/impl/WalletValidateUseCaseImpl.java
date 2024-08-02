package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.WalletValidateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletValidateUseCaseImpl implements WalletValidateUseCase {
    private final WalletService walletService;

    @Override
    public void validatedBalance(Long userId, Double price) {
        walletService.validatedBalance(userId, price);
    }
}
