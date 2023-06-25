package com.ably.dto.wishdraw.response;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishDrawSearchResponse {

    private List<WishDrawSearchDto> content;
    private int currentPage;
    private boolean isLast;
}
