package com.ably.dto.wish.request;

import com.ably.dto.common.PaginationType;
import com.ably.dto.common.SearchPaginationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Schema(description = "찜 검색 요청")
public class WishSearchRequest implements SearchPaginationDto {

    @Schema(description = "유저 ID", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private Long memberId;

    @Schema(description = "찜 서랍 ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "찜 서랍 ID는 필수 값 입니다.")
    private Long wishDrawId;

    @Schema(description = "페이지 번호", defaultValue = "0")
    @Min(value = 0, message = "페이지 번호는 0이상만 가능 합니다.")
    private int page;

    @Schema(description = "페이지 사이즈", defaultValue = "30")
    @Max(value = 30, message = "페이지 크기는 30이하만 가능합니다.")
    private int limit;

    private PaginationType paginationType;

    @Override
    @JsonIgnore
    public Pageable getPageable() {
        return PageRequest.of(page, limit);
    }
}
