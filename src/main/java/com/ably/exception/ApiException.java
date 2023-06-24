package com.ably.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@AllArgsConstructor
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ApiException extends RuntimeException{

    private int code;
    private HttpStatus httpStatus;
    private String message;
    private String customMessage;

    public ApiException(ApiError apiError){
        this.code = apiError.getCode();
        this.httpStatus = apiError.getHttpStatus();
        this.message = apiError.getMessage();
    }

    public ApiException(ApiError apiError, String customMessage){
        this.code = apiError.getCode();
        this.httpStatus = apiError.getHttpStatus();
        this.message = apiError.getMessage();
        this.customMessage = customMessage;
    }
}
