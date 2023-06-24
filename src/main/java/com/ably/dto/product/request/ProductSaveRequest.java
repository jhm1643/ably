package com.ably.dto.product.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class ProductSaveRequest {

    private String name;
    private String thumbnail;
    private Long price;
}
