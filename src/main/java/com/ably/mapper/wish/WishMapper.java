package com.ably.mapper.wish;

import com.ably.dto.wish.WishSearchDto;
import com.ably.dto.wish.response.WishSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishMapper {

    @Mapping(target = "currentPage", source = "dto.number")
    @Mapping(target = "isLast", source = "dto.last")
    WishSearchResponse toResponse(Slice<WishSearchDto> dto);
}
