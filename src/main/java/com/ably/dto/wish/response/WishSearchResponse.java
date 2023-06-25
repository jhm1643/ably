package com.ably.dto.wish.response;

import com.ably.dto.wish.WishSearchDto;
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
@Schema(description = "찜 검색 응답")
public class WishSearchResponse {

    @Schema(description = "찜 목록")
    private List<WishSearchDto> content;

    @Schema(description = "현재 페이지 번호")
    private int currentPage;

    @Schema(description = "다음 페이지 존재 유무")
    private boolean isLast;
}
