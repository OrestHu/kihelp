package org.example.kihelp.wallet.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.model.User;
import org.example.kihelp.wallet.exception.WalletAlreadyExist;
import org.example.kihelp.wallet.exception.WalletNotFoundException;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.model.req.WalletRequest;
import org.example.kihelp.wallet.respository.WalletRepository;
import org.example.kihelp.wallet.service.WalletService;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.example.kihelp.wallet.utils.MessageError.WALLET_ALREADY_EXISTS;
import static org.example.kihelp.wallet.utils.MessageError.WALLET_NOT_FOUND;


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

    @Override
    public List<Wallet> getWallets() {
        return walletRepository.findAll();
    }

    @Override
    public Wallet getWallet(Integer walletId) {
        return walletRepository.findById(walletId)
                .orElseThrow(() -> new WalletNotFoundException(WALLET_NOT_FOUND));
    }

    @Override
    public Wallet getWalletByUser(User user) {
        return walletRepository.findByUser(user)
                .orElseThrow(() -> new WalletNotFoundException(WALLET_NOT_FOUND));
    }

    @Override
    public void updateWallet(Integer walletId, WalletRequest walletRequest) {
        var wallet = getWallet(walletId);

        if(walletRequest.amount() != null){
            wallet.setAmount(walletRequest.amount());
        }

        walletRepository.save(wallet);
    }

    @Override
    public void updateWalletByUser(User user, WalletRequest walletRequest) {
        var wallet = getWalletByUser(user);

        if(walletRequest.amount() != null){
            wallet.setAmount(walletRequest.amount());
        }

        walletRepository.save(wallet);
    }
}
