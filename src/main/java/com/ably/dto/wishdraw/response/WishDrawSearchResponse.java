package com.ably.dto.wishdraw.response;

import com.ably.dto.common.pagination.response.PaginationResponse;
import com.ably.dto.wishdraw.WishDrawSearchDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
@Getter
@Schema(description = "찜 서랍 검색 응답")
public class WishDrawSearchResponse extends PaginationResponse {

    @Schema(description = "찜 서랍 목록")
    private List<WishDrawSearchDto> content;
}
