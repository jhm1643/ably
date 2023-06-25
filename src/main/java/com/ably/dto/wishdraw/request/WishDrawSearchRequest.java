package com.ably.dto.wishdraw.request;

import com.ably.dto.common.SearchPaginationDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
@Schema(description = "찜 서랍 검색 요청")
public class WishDrawSearchRequest implements SearchPaginationDto {

    @Schema(description = "유저 ID")
    @JsonIgnore
    private Long memberId;

    @Schema(description = "페이지 번호", defaultValue = "0")
    @Min(value = 0, message = "페이지 번호는 0이상만 가능 합니다.")
    private int page;

    @Schema(description = "페이지 사이즈", defaultValue = "30")
    @Max(value = 30, message = "페이지 크기는 30이하만 가능합니다.")
    private int limit;

    @Override
    public Pageable getPageable() {
        return PageRequest.of(page, limit);
    }
}
