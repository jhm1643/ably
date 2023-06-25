package com.ably.repository.custom;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import org.springframework.data.domain.Slice;

public interface WishDrawRepositoryCustom {

    Slice<WishDrawSearchDto> findWishDrawPagingList(WishDrawSearchRequest request);
}
