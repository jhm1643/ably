package com.ably.dto.wish.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WishSaveRequest {

    @NotNull(message = "상품 번호는 필수 입니다.")
    private Long productId;

    @NotNull(message = "찜 서랍 번호는 필수 입니다.")
    private Long wishDrawId;

    @Setter
    @JsonIgnore
    private Long memberId;
}
