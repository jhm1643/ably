package com.ably.dto.wish.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "찜 추가 요청")
public class WishSaveRequest {

    @Schema(description = "상품 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "상품 번호는 필수 입니다.")
    private Long productId;

    @Schema(description = "찜 서랍 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "찜 서랍 번호는 필수 입니다.")
    private Long wishDrawId;

    @Setter
    @Schema(description = "유저 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    @JsonIgnore
    private Long memberId;
}
