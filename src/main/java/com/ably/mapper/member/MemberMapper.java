package com.ably.mapper.member;

import com.ably.dto.user.response.MemberSearchResponse;
import com.ably.entity.Member;
import org.mapstruct.Mapper;

import static org.mapstruct.MappingConstants.ComponentModel.SPRING;

@Mapper(componentModel = SPRING)
public interface MemberMapper {

    MemberSearchResponse toResponse(Member member);
}
