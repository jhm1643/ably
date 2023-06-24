package com.ably.security.filter.exception;

import com.ably.exception.ApiError;
import com.ably.exception.ApiException;
import com.ably.exception.ApiExceptionHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;


@RequiredArgsConstructor
@Component
public class AuthExceptionHandler {

    private static final String CONTENT_TYPE_APPLICATION_JSON = "application/json;charset=UTF-8";
    private final ObjectMapper objectMapper;

    @SneakyThrows
    public void exceptionHandler(HttpServletResponse response, Exception e){
        ApiError apiError;

        if (e.getClass().equals(ExpiredJwtException.class))
            apiError = ApiError.AUTH_TOKEN_EXPIRE;
        else if(AuthenticationException.class.isAssignableFrom(e.getClass()))
            apiError = ApiError.NOT_AUTHENTICATION;
        else if(AccessDeniedException.class.isAssignableFrom(e.getClass()))
            apiError = ApiError.ACCESS_DENIED;
        else if(e.getClass().equals(ApiException.class))
            apiError = ApiError.ofCode(((ApiException) e).getCode());
        else
            apiError = ApiError.BAD_AUTH_TOKEN;

        response.setContentType(CONTENT_TYPE_APPLICATION_JSON);
        response.getWriter().write(objectMapper.writeValueAsString(ApiExceptionHandler.ErrorResponse.builder()
                .code(apiError.getCode())
                .message(apiError.getMessage())
                .build()));
        response.setStatus(apiError.getHttpStatus().value());
    }
}
