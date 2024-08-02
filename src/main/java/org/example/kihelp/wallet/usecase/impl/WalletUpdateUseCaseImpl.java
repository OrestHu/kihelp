package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.WalletUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletUpdateUseCaseImpl implements WalletUpdateUseCase {
    private final WalletService walletService;
    private final UserApiService userApiService;

    @Override
    public void updateWallet(Integer walletId, WalletRequest walletRequest) {
        walletService.updateWallet(walletId, walletRequest);
    }

    @Override
    public void updateWalletByUser(String telegramId, WalletRequest walletRequest) {
        var user = userApiService.currentUserAccountByTelegramId(telegramId);

        walletService.updateWalletByUser(user.userId(), walletRequest);
    }

    @Override
    public void topUpWallet(Long userId, WalletRequest walletRequest) {
        walletService.topUpWallet(userId, walletRequest);
    }
}
