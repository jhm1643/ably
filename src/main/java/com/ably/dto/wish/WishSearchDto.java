package com.ably.dto.wish;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishSearchDto {

    private Long wishId;
    private Long productId;
    private String productName;
    private String productThumbnail;
    private Long productPrice;
}
