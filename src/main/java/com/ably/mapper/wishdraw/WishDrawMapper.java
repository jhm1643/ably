package com.ably.mapper.wishdraw;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishDrawMapper {

    @Mapping(target = "currentPage", source = "dto.number")
    @Mapping(target = "isLast", source = "dto.last")
    WishDrawSearchResponse toResponse(Slice<WishDrawSearchDto> dto);
}
