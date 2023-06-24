package com.ably.dto.wish.response;

import com.ably.entity.Product;
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

    private List<WishResponse> content;
    private int currentPage;
    private boolean isLast;

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class WishResponse {
        private Long wishId;
        private Product product;
    }

}
