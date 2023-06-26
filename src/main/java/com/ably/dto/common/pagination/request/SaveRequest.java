package com.ably.dto.common.pagination.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
public class SaveRequest {

    @Schema(description = "유저 ID", hidden = true)
    public Long memberId;

    @Schema(description = "관리자 ID", hidden = true)
    public Long adminId;
}
