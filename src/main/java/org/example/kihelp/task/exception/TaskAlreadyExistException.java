package org.example.kihelp.task.exception;

public class TaskAlreadyExistException extends RuntimeException {
    public TaskAlreadyExistException(String message) {
        super(message);
    }
}
