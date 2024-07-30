package org.example.kihelp.task.exception;

public class MassageNotSentToTelegramException extends RuntimeException {
    public MassageNotSentToTelegramException(String message) {
        super(message);
    }
}
