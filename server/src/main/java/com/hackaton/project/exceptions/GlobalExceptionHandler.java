package com.hackaton.project.exceptions;

import com.hackaton.project.dtos.common.ErrorDTO;
import com.hackaton.project.exceptions.business.BusinessDoesNotExistsException;
import com.hackaton.project.exceptions.business.BusinessExistsException;
import com.hackaton.project.exceptions.business.InvalidBusinessDataException;
import com.hackaton.project.exceptions.common.EntityNotFoundException;
import com.hackaton.project.exceptions.common.InsufficientPermissionsException;
import com.hackaton.project.exceptions.jwts.JWTInvalidException;
import com.hackaton.project.exceptions.student.InvalidStudentDataException;
import com.hackaton.project.exceptions.student.StudentDoesNotExistsException;
import com.hackaton.project.exceptions.student.StudentExistsException;
import com.hackaton.project.exceptions.user.UserIsAuthenticatedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDTO> handleNotFoundException(EntityNotFoundException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Entity Not Found")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withErrorType(EntityNotFoundException.class.getSimpleName())
                        .withErrorCode("ENF0")
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(JWTInvalidException.class)
    public ResponseEntity<ErrorDTO> handleJWTInvalidException(JWTInvalidException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Invalid JWT")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.UNAUTHORIZED.value())
                        .withErrorType(JWTInvalidException.class.getSimpleName())
                        .withErrorCode("JIE0")
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }

    @ExceptionHandler(StudentExistsException.class)
    public ResponseEntity<ErrorDTO> handleStudentExistsException(StudentExistsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Student Already Exists")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.CONFLICT.value())
                        .withErrorType(StudentExistsException.class.getSimpleName())
                        .withErrorCode("SEE0")
                        .build(),
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(InvalidStudentDataException.class)
    public ResponseEntity<ErrorDTO> handleInvalidStudentData(InvalidStudentDataException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Invalid credentials")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withErrorType(InvalidStudentDataException.class.getSimpleName())
                        .withErrorCode("ISD0")
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }
    @ExceptionHandler(BusinessExistsException.class)
    public ResponseEntity<ErrorDTO> handleBusinessExistsException(BusinessExistsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Business Already Exists")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.CONFLICT.value())
                        .withErrorType(BusinessExistsException.class.getSimpleName())
                        .withErrorCode("BEE0")
                        .build(),
                HttpStatus.CONFLICT
        );
    }
    @ExceptionHandler(InvalidBusinessDataException.class)
    public ResponseEntity<ErrorDTO> handleInvalidBusinessData(InvalidBusinessDataException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Invalid credentials")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.BAD_REQUEST.value())
                        .withErrorType(InvalidBusinessDataException.class.getSimpleName())
                        .withErrorCode("IBD0")
                        .build(),
                HttpStatus.BAD_REQUEST
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
    @ExceptionHandler(InsufficientPermissionsException.class)
    public ResponseEntity<ErrorDTO> InsufficientPermissionsException(InsufficientPermissionsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("Insufficient Permissions")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.FORBIDDEN.value())
                        .withErrorType(InsufficientPermissionsException.class.getSimpleName())
                        .withErrorCode("IPE0")
                        .build(),
                HttpStatus.FORBIDDEN
        );
    }
    @ExceptionHandler(UserIsAuthenticatedException.class)
    public ResponseEntity<ErrorDTO> UserIsAuthenticatedException(UserIsAuthenticatedException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("User Is Not Authenticated")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.UNAUTHORIZED.value())
                        .withErrorType(UserIsAuthenticatedException.class.getSimpleName())
                        .withErrorCode("UINA")
                        .build(),
                HttpStatus.UNAUTHORIZED
        );
    }
    @ExceptionHandler(StudentDoesNotExistsException.class)
    public ResponseEntity<ErrorDTO> StudentDoesNotExistsException(StudentDoesNotExistsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("User doesn't exist")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withErrorType(UserIsAuthenticatedException.class.getSimpleName())
                        .withErrorCode("SDNE")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
    @ExceptionHandler(BusinessDoesNotExistsException.class)
    public ResponseEntity<ErrorDTO> BusinessDoesNotExistsException(BusinessDoesNotExistsException exception) {
        return new ResponseEntity<>(
                ErrorDTO.builder()
                        .withTitle("User doesn't exist")
                        .withDetails(exception.getMessage())
                        .withStatus(HttpStatus.NOT_FOUND.value())
                        .withErrorType(UserIsAuthenticatedException.class.getSimpleName())
                        .withErrorCode("BDNE")
                        .build(),
                HttpStatus.NOT_FOUND
        );
    }
}
