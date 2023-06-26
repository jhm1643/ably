package com.ably.dto.wish.request;

import com.ably.dto.common.pagination.request.PaginationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "찜 검색 요청")
public class WishSearchRequest extends PaginationRequest {

    @Schema(description = "찜 서랍 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "찜 서랍 ID는 필수 값 입니다.")
    private Long wishDrawId;
}
