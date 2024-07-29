package org.example.kihelp.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.exception.WalletAlreadyExist;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.respository.WalletRepository;
import org.example.kihelp.wallet.service.WalletService;
import org.springframework.stereotype.Service;

import static org.example.kihelp.wallet.utils.MessageError.WALLET_ALREADY_EXISTS;


@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {
    private final WalletRepository walletRepository;

    @Override
    public void createWallet(User user, Wallet wallet) {
        var exist = walletRepository.existsByUser(user);

        if (exist){
            throw new WalletAlreadyExist(WALLET_ALREADY_EXISTS);
        }

        wallet.setUser(user);

        walletRepository.save(wallet);
    }
}
