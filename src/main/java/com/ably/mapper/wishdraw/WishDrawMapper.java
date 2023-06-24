package com.ably.mapper.wishdraw;

import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import com.ably.entity.WishDraw;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishDrawMapper {

    @Mapping(target = "content", source = "wishDraws.content")
    @Mapping(target = "currentPage", source = "wishDraws.number")
    @Mapping(target = "isLast", source = "wishDraws.last")
    WishDrawSearchResponse toResponse(Slice<WishDraw> wishDraws);

    @Mapping(target = "wishDrawId", source = "wishDraw.id")
    @Mapping(target = "wishDrawName", source = "wishDraw.name")
    WishDrawSearchResponse.WishDrawResponse toWishDrawResponse(WishDraw wishDraw);
}
