package org.example.kihelp.wallet.exception;

public class NotEnoughAmountException extends RuntimeException {
    public NotEnoughAmountException(String message) {
        super(message);
    }
}
