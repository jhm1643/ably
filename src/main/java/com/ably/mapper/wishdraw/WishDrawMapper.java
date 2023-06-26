package com.ably.mapper.wishdraw;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishDrawMapper {

    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "isLast", source = "last")
    WishDrawSearchResponse toScrollResponse(Slice<WishDrawSearchDto> dto);

    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "hasNext", expression = "java( dto.hasNext() )")
    @Mapping(target = "hasPrevious", expression = "java( dto.hasPrevious() )")
    WishDrawSearchResponse toPageResponse(Page<WishDrawSearchDto> dto);

    default WishDrawSearchResponse toResponse(Slice<WishDrawSearchDto> dto){
        if(dto instanceof Page){
            return toPageResponse((Page) dto);
        }
        return toScrollResponse(dto);
    }
}
