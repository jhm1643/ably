package com.ably.dto.wishdraw.request;

import com.ably.dto.common.pagination.request.SaveRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "찜 서랍 추가 요청")
public class WishDrawSaveRequest extends SaveRequest {

    @Schema(description = "찜 서랍 이름", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "찜 서랍 이름은 필수 입니다.")
    private String wishDrawName;
}
