package com.ably.dto.user.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder @NoArgsConstructor @AllArgsConstructor
@Getter
public class SignUpRequest {

    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String email;

    @NotBlank(message = "패스워드는 필수 값습니다.")
    @Setter
    private String password;
}
