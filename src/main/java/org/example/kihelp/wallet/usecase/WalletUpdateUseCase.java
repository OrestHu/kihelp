package org.example.kihelp.wallet.usecase;

import jakarta.validation.Valid;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.springframework.validation.annotation.Validated;

@Validated
public interface WalletUpdateUseCase {
    void updateWallet(Integer walletId, @Valid WalletRequest walletRequest);
    void updateWalletByUser(String telegramId, @Valid WalletRequest walletRequest);
}
