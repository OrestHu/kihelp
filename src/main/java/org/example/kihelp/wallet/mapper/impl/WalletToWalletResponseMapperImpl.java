package org.example.kihelp.wallet.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.example.kihelp.wallet.mapper.WalletToWalletResponseMapper;
import org.example.kihelp.wallet.model.Wallet;
import org.example.kihelp.wallet.model.resp.WalletResponse;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletToWalletResponseMapperImpl implements WalletToWalletResponseMapper {

    @Override
    public WalletResponse map(Wallet wallet) {
        return new WalletResponse(
                wallet.getId(),
                wallet.getAmount()
        );
    }
}
