package com.ably.dto.wishdraw.request;

import com.ably.dto.common.SearchPaginationDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

@Getter
@Setter
public class WishDrawSearchRequest implements SearchPaginationDto {

    private Long memberId;

    @Min(value = 0, message = "페이지 번호는 0이상만 가능 합니다.")
    private int page;

    @Max(value = 30, message = "페이지 크기는 30이하만 가능합니다.")
    private int limit;

    @Override
    public Pageable getPageable() {
        return PageRequest.of(page, limit);
    }
}