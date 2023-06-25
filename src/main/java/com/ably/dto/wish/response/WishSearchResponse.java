package com.ably.dto.wish.response;

import com.ably.dto.wish.WishSearchDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishSearchResponse {

    private List<WishSearchDto> content;
    private int currentPage;
    private boolean isLast;
}
