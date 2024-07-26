package org.example.kihelp.user.exception;


import org.example.kihelp.subject.exception.SubjectAlreadyExist;
import org.example.kihelp.subject.exception.SubjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends Throwable {

    @ExceptionHandler(RuntimeException.class)
    public ProblemDetail handlerException(RuntimeException ex) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail handlerBadCredentialsException() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ProblemDetail handlerJwtTokenExpiredException() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ProblemDetail handlerRoleNotFoundException() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ProblemDetail handlerUserNotFoundException() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(SubjectAlreadyExist.class)
    public ProblemDetail handlerSubjectAlreadyExist() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(SubjectNotFoundException.class)
    public ProblemDetail handlerSubjectNotFoundException() {
        return ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Unknown error occurred");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = "error";
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
