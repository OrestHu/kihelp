package org.example.kihelp.wallet.usecase;

import org.example.kihelp.wallet.model.req.WalletRequest;

public interface WalletUpdateUseCase {
    void updateWallet(Integer walletId, WalletRequest walletRequest);
}
