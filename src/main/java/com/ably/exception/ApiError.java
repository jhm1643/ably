package com.ably.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum ApiError {

    //400
    ALREADY_EXIST_DATA(4001, HttpStatus.BAD_REQUEST, "이미 존재하는 데이터 입니다."),
    INVALID_USER_ACCOUNT(4001, HttpStatus.BAD_REQUEST, "아이디/비밀번호를 다시 확인해주세요."),
    NOT_APPROVED_USER_ACCOUNT(4002, HttpStatus.BAD_REQUEST,"승인되지 않은 사용자 입니다."),
    BAD_AUTH_TOKEN(4003, HttpStatus.BAD_REQUEST, "잘못된 인증 토큰 요청입니다."),

    //HTTP STATUS CODE 401
    NOT_FOUND_USER(4011, HttpStatus.UNAUTHORIZED, "아이디/비밀번호를 다시 확인해주세요."),
    NOT_AUTHENTICATION(4012, HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자 입니다."),
    ACCESS_DENIED(4012, HttpStatus.UNAUTHORIZED, "인가되지 않은 사용자 입니다."),
    AUTH_TOKEN_EXPIRE(4011, HttpStatus.UNAUTHORIZED, "토큰 유효기간이 만료되었습니다."),
    NOT_AUTH_TOKEN_EXPIRE(4012, HttpStatus.UNAUTHORIZED, "토큰 유효기간이 아직 만료되지 않았습니다."),

    //404
    NOT_EXIST_DATA(4041, HttpStatus.NOT_FOUND, "존재하지 않는 데이터 입니다.")
    ;

    private int code;
    private HttpStatus httpStatus;
    private String message;

    public static ApiError ofCode(int code){
        return Arrays.stream(ApiError.values())
                .filter(apiError -> apiError.getCode() == code)
                .findFirst()
                .orElse(null);
    }
}
