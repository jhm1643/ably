package com.ably.dto.common.pagination.request;

import com.ably.dto.common.pagination.type.PaginationType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class PaginationRequest {

    @Schema(description = "페이지 번호", defaultValue = "0")
    @Min(value = 0, message = "페이지 번호는 0이상만 가능 합니다.")
    private int page;

    @Schema(description = "페이지 사이즈", defaultValue = "30")
    @Max(value = 30, message = "페이지 크기는 30이하만 가능합니다.")
    private int limit;

    @Schema(description = "페이지네이션 유형", defaultValue = "SCROLL")
    private PaginationType paginationType = PaginationType.SCROLL;

    @Schema(description = "유저 ID", hidden = true)
    public Long memberId;

    @Schema(description = "관리자 ID", hidden = true)
    public Long adminId;

    public Pageable getPageable() {

        return PageRequest.of(page, limit);
    }
}
