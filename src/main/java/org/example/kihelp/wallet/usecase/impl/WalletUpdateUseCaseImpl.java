package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.service.UserService;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.WalletUpdateUseCase;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletUpdateUseCaseImpl implements WalletUpdateUseCase {
    private final WalletService walletService;
    private final UserService userService;

    @Override
    public void updateWallet(Integer walletId, WalletRequest walletRequest) {
        walletService.updateWallet(walletId, walletRequest);
    }

    @Override
    public void updateWalletByUser(String userUUID, WalletRequest walletRequest) {
        var user = userService.getUserByUserUUID(userUUID);

        walletService.updateWalletByUser(user, walletRequest);
    }
}
