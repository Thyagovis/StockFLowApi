package com.stockflow.StockFlowApi.shared.exceptions;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(AuthenticationException.class)
    private ResponseEntity<ErrorMessageResponse> handleAuthenticationException(AuthenticationException exception, HttpServletRequest request) {

        var response = new ErrorMessageResponse(
                LocalDateTime.now(),
                401,
                "Unauthorized",
                "Falha ao Autenticar: "+exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<ErrorMessageResponse> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {

        var response = new ErrorMessageResponse(
                LocalDateTime.now(),
                403,
                "Forbidden",
                "Acesso negado",
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorMessageResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex, HttpServletRequest request) {

        var fieldError = ex.getBindingResult().getFieldError();

        var response = new ErrorMessageResponse(
                LocalDateTime.now(),
                400,
                "Bad Request",
                "Corpo JSON Invalido - "+(fieldError == null ? "" : fieldError.getField()+": "+fieldError.getRejectedValue()),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<ErrorMessageResponse> handleConflictException(ConflictException exception, HttpServletRequest request) {

        var response = new ErrorMessageResponse(
                LocalDateTime.now(),
                409,
                "Conflict",
                "Conflito: "+exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorMessageResponse> handleNotFoundException(NotFoundException exception, HttpServletRequest request) {

        var response = new ErrorMessageResponse(
                LocalDateTime.now(),
                404,
                "Not Found",
                "Não encontrado: "+exception.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

}
