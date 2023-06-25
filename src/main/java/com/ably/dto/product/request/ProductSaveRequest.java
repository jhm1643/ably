package com.ably.dto.product.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "상품 저장")
public class ProductSaveRequest {

    @Schema(description = "상품 명")
    private String name;

    @Schema(description = "상품 썸네일 이미지 주소")
    private String thumbnail;

    @Schema(description = "상품 가격")
    private Long price;
}
