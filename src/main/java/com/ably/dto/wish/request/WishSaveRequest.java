package com.ably.dto.wish.request;

import com.ably.dto.common.pagination.request.SaveRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "찜 추가 요청")
public class WishSaveRequest extends SaveRequest {

    @Schema(description = "상품 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "상품 번호는 필수 입니다.")
    private Long productId;

    @Schema(description = "찜 서랍 번호", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "찜 서랍 번호는 필수 입니다.")
    private Long wishDrawId;
}
