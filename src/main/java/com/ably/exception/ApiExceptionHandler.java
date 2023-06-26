package com.ably.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ApiExceptionHandler {

    @Getter
    @Builder
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ErrorResponse {
        private int code;
        private String message;
        private String customMessage;
        private List<BindingResultError> bindingErrorMessage;
        private String path;
        private String stackTrace;

        @JsonIgnore
        private HttpStatus httpStatus;
    }

    @Builder
    @Getter
    public static class BindingResultError{
        private String field;
        private String message;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> methodValidException(MethodArgumentNotValidException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .bindingErrorMessage(getBindingResultErrors(exception.getBindingResult()))
                        .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponse> handleDataIntegrityViolationException(DataIntegrityViolationException exception){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(ApiError.ALREADY_EXIST_DATA.getCode())
                        .message(String.format("%s => (%s)", ApiError.ALREADY_EXIST_DATA.getMessage(), exception.getMessage()))
                        .build());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleException() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.builder()
                        .code(ApiError.INVALID_USER_ACCOUNT.getCode())
                        .message(ApiError.INVALID_USER_ACCOUNT.getMessage())
                        .build());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> handleApiException(ApiException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(ErrorResponse.builder()
                        .code(exception.getCode())
                        .message(exception.getMessage())
                        .customMessage(exception.getCustomMessage())
                        .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception exception, WebRequest request) {
        return ResponseEntity
                .status(MissingRequestValueException.class.isAssignableFrom(exception.getClass()) ? HttpStatus.BAD_REQUEST : HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.builder()
                        .path(request.getDescription(false))
                        .message(exception.getMessage())
                        .stackTrace(ExceptionUtils.getStackTrace(exception))
                        .build());
    }

    private List<BindingResultError> getBindingResultErrors(BindingResult bindingResult){
        return bindingResult.getFieldErrors().stream()
                .map(fieldError -> BindingResultError.builder()
                        .field(fieldError.getField())
                        .message(fieldError.getDefaultMessage())
                        .build())
                .collect(Collectors.toList());
    }
}
