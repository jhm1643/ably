package com.ably.dto.wish.response;

import com.ably.dto.common.pagination.response.PaginationResponse;
import com.ably.dto.wish.WishSearchDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Collections;
import java.util.List;

@SuperBuilder
@Getter
@Schema(description = "찜 검색 응답")
public class WishSearchResponse extends PaginationResponse {

    @Schema(description = "찜 목록")
    @Builder.Default
    private List<WishSearchDto> content = Collections.emptyList();
}
