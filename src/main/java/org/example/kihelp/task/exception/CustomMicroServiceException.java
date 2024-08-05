package org.example.kihelp.task.exception;

public class CustomMicroServiceException extends RuntimeException{
    public CustomMicroServiceException(String message) {
        super(message);
    }
}
