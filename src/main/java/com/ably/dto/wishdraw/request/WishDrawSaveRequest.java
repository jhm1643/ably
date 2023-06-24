package com.ably.dto.wishdraw.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
public class WishDrawSaveRequest {

    @NotBlank(message = "찜 서랍 이름은 필수 입니다.")
    private String wishDrawName;
}
