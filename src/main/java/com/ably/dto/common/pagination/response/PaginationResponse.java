package com.ably.dto.common.pagination.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class PaginationResponse {

    @Schema(description = "현재 페이지 번호")
    private Integer currentPage;

    @Schema(description = "총 페이지 수 (only page)")
    private Integer totalPages;

    @Schema(description = "총 row 수 (only page)" )
    private Long totalElements;

    @Schema(description = "다음 페이지 존재 여부 (only page)")
    private Boolean hasNext;

    @Schema(description = "이전 페이지 존재 여부 (only page)")
    private Boolean hasPrevious;

    @Schema(description = "마지막 페이지 여부 (only scroll)")
    private Boolean isLast;
}
