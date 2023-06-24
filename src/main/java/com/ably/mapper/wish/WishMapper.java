package com.ably.mapper.wish;

import com.ably.dto.wish.response.WishSearchResponse;
import com.ably.entity.Wish;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishMapper {

    @Mapping(target = "currentPage", source = "wishes.number")
    @Mapping(target = "isLast", source = "wishes.last")
    WishSearchResponse toResponse(Slice<Wish> wishes);

    @Mapping(target = "wishId", source = "wish.id")
    WishSearchResponse.WishResponse toWishResponse(Wish wish);
}
