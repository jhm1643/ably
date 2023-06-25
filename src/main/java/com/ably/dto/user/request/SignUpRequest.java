package com.ably.dto.user.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Schema(description = "회원가입 요청")
public class SignUpRequest {

    @Schema(description = "이메일", requiredMode = Schema.RequiredMode.REQUIRED)
    @Email(message = "올바른 이메일 형식으로 입력해주세요.")
    private String email;

    @Schema(description = "패스워드", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "패스워드는 필수 값 입니다.")
    @Setter
    private String password;
}
