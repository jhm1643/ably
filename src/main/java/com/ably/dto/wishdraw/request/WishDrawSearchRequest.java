package com.ably.dto.wishdraw.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WishDrawSearchRequest {

    @Min(value = 0, message = "페이지 번호는 0이상만 가능 합니다.")
    private int page;

    @Max(value = 30, message = "페이지 크기는 30이하만 가능합니다.")
    private int limit;
}
