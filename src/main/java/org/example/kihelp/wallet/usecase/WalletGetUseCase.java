package org.example.kihelp.wallet.usecase;

import org.example.kihelp.wallet.model.resp.WalletResponse;

import java.util.List;

public interface WalletGetUseCase {
    List<WalletResponse> getWallets();
    WalletResponse getWallet(Integer walletId);
    WalletResponse getWalletByUser();
}
