package com.ably.mapper.wish;

import com.ably.dto.wish.WishSearchDto;
import com.ably.dto.wish.response.WishSearchResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface WishMapper {

    @Mapping(target = "currentPage", source = "dto.number")
    @Mapping(target = "isLast", source = "dto.last")
    WishSearchResponse toScrollResponse(Slice<WishSearchDto> dto);

    @Mapping(target = "currentPage", source = "number")
    @Mapping(target = "hasNext", expression = "java( dto.hasNext() )")
    @Mapping(target = "hasPrevious", expression = "java( dto.hasPrevious() )")
    WishSearchResponse toPageResponse(Page<WishSearchDto> dto);

    default WishSearchResponse toResponse(Slice<WishSearchDto> dto){
        if(dto instanceof Page){
            return toPageResponse((Page) dto);
        }
        return toScrollResponse(dto);
    }
}
