package com.ably.repository.custom;

import com.ably.dto.wish.WishSearchDto;
import com.ably.dto.wish.request.WishSearchRequest;
import org.springframework.data.domain.Slice;

public interface WishRepositoryCustom {

    Slice<WishSearchDto> findWishPagingList(WishSearchRequest request);
}
