package org.example.kihelp.user.exception;


import org.example.kihelp.subject.exception.SubjectAlreadyExist;
import org.example.kihelp.subject.exception.SubjectNotFoundException;
import org.example.kihelp.task.exception.*;
import org.example.kihelp.teacher.exception.TeacherAlreadyExist;
import org.example.kihelp.teacher.exception.TeacherNotFoundException;
import org.example.kihelp.wallet.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handlerException(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CustomMicroServiceException.class)
    public ResponseEntity<Map<String, String>> handleCustomMicroServiceException(CustomMicroServiceException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleRoleNotFoundException(RoleNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUserNotFoundException(UserNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubjectAlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleSubjectAlreadyExist(SubjectAlreadyExist ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleSubjectNotFoundException(SubjectNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherAlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleTeacherAlreadyExist(TeacherAlreadyExist ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleTransactionAlreadyExistException(TransactionAlreadyExistException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MassageNotSentToTelegramException.class)
    public ResponseEntity<Map<String, String>> handleMassageNotSentToTelegramException(MassageNotSentToTelegramException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTransactionNotFoundException(TransactionNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotEnoughAmountException.class)
    public ResponseEntity<Map<String, String>> handleNotEnoughAmountException(NotEnoughAmountException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTeacherNotFoundException(TeacherNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleTaskAlreadyExistException(TaskAlreadyExistException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TaskNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleTaskNotFoundException(TaskNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DirectoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleDirectoryNotFoundException(DirectoryNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArgumentAlreadyExistException.class)
    public ResponseEntity<Map<String, String>> handleArgumentAlreadyExistException(ArgumentAlreadyExistException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ArgumentNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleArgumentNotFoundException(ArgumentNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletAlreadyExist.class)
    public ResponseEntity<Map<String, String>> handleWalletAlreadyExist(WalletAlreadyExist ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleWalletNotFoundException(WalletNotFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = "error";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
