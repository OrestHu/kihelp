package org.example.kihelp.wallet.exception;

public class WalletAlreadyExist extends RuntimeException {
    public WalletAlreadyExist(String message) {
        super(message);
    }
}
