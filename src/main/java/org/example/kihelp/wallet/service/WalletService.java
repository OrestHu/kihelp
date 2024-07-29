package org.example.kihelp.wallet.service;

import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.model.Wallet;

public interface WalletService {
    void createWallet(User user, Wallet wallet);
}
