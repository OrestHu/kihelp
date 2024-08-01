package org.example.kihelp.wallet.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.user.api.service.UserApiService;
import org.example.kihelp.wallet.mapper.WalletToWalletResponseMapper;
import org.example.kihelp.wallet.model.resp.WalletResponse;
import org.example.kihelp.wallet.service.WalletService;
import org.example.kihelp.wallet.usecase.WalletGetUseCase;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class WalletGetUseCaseImpl implements WalletGetUseCase {
    private final WalletService walletService;
    private final UserApiService userApiService;
    private final WalletToWalletResponseMapper walletToWalletResponseMapper;

    @Override
    public List<WalletResponse> getWallets() {
        var wallets = walletService.getWallets();

        return wallets.stream().map(walletToWalletResponseMapper::map).toList();
    }

    @Override
    public WalletResponse getWallet(Integer walletId) {
        var wallet = walletService.getWallet(walletId);

        return walletToWalletResponseMapper.map(wallet);
    }

    @Override
    public WalletResponse getWalletByUser() {
        var userId = userApiService.currentUserAccount().userId();
        var wallet = walletService.getWalletByUser(userId);

        return walletToWalletResponseMapper.map(wallet);
    }
}
