package com.ably.dto.user.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "토큰 응답")
public class JwtTokenResponse {

    @Schema(description = "jwt access token")
    private String accessToken;

    @Schema(description = "jwt refresh token")
    private String refreshToken;
}
