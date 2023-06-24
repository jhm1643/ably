package com.ably.dto.wishdraw.response;

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

    private List<WishDrawResponse> content;
    private int currentPage;
    private boolean isLast;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class WishDrawResponse {
        private Long wishDrawId;
        private String wishDrawName;
    }

}
