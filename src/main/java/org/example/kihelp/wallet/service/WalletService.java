package org.example.kihelp.wallet.service;

import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.model.req.WalletRequest;

import java.util.List;

public interface WalletService {
    void createWallet(User user, Wallet wallet);
    List<Wallet> getWallets();
    Wallet getWallet(Integer walletId);
    Wallet getWalletByUser(Long userId);
    void updateWallet(Integer walletId, WalletRequest walletRequest);
    void updateWalletByUser(Long userId, WalletRequest walletRequest);
}
