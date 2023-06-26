package com.ably.dto.wishdraw.request;

import com.ably.dto.common.pagination.request.PaginationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "찜 서랍 검색 요청")
public class WishDrawSearchRequest extends PaginationRequest {

}
