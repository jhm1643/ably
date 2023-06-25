package com.ably.dto.wishdraw.response;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Schema(description = "찜 서랍 검색 응답")
public class WishDrawSearchResponse {

    @Schema(description = "찜 서랍 목록")
    private List<WishDrawSearchDto> content;

    @Schema(description = "현재 페이지 번호")
    private int currentPage;

    @Schema(description = "다음 페이지 존재 유무")
    private boolean isLast;
}
