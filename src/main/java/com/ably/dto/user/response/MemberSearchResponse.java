package com.ably.dto.user.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder @AllArgsConstructor @NoArgsConstructor
@Getter
public class MemberSearchResponse {

    private String email;
}
