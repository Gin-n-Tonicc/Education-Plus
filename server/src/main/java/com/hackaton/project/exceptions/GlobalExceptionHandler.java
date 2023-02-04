package com.hackaton.project.exceptions;

import com.hackaton.project.dtos.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserExistsException.class)
    public ResponseEntity<ErrorDTO> handleUserExistsException(UserExistsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("User Already Exists")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.CONFLICT.value())
                        .withErrorType(UserExistsException.class.getSimpleName())
                        .withErrorCode("UEE0")
                        .build(),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ProblemDetail body = exception.getBody();

        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle(body.getTitle())
                        .withDetails(body.getDetail())
                        .withStatus(body.getStatus())
                        .withErrorType(MethodArgumentNotValidException.class.getSimpleName())
                        .withErrorCode("ANVE")
                        .build(),
                HttpStatusCode.valueOf(body.getStatus())
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleOtherExceptions(Exception exception) {
        exception.printStackTrace();

        return new ResponseEntity<>(
            ErrorDTO.builder()
                    .withTitle("Internal Server Error")
                    .withDetails("Uncaught exception")
                    .withStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .withErrorType("InternalServerError")
                    .withErrorCode("ISE0")
                    .build(),
            HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
