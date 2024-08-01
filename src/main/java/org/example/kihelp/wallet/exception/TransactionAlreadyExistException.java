package org.example.kihelp.wallet.exception;

public class TransactionAlreadyExistException extends RuntimeException {
    public TransactionAlreadyExistException(String message) {
        super(message);
    }
}
