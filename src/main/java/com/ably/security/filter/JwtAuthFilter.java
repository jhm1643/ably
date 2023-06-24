package com.ably.security.filter;

import com.ably.security.JwtProvider;
import com.ably.security.filter.exception.AuthExceptionHandler;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_TYPE = "Bearer ";

    private final JwtProvider jwtProvider;
    private final AuthExceptionHandler authExceptionHandler;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        try{
            if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_TYPE)) {
                String token = bearerToken.substring(BEARER_TYPE.length());
                jwtProvider.tokenAuthentication(token);
            }
        }catch (Exception e) {
            authExceptionHandler.exceptionHandler(response, e);
            return;
        }

        filterChain.doFilter(request, response);
    }
}
