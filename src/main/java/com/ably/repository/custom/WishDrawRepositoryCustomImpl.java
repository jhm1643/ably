package com.ably.repository.custom;

import com.ably.dto.wishdraw.WishDrawSearchDto;
import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import com.ably.util.PagingUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;

import static com.ably.entity.QWishDraw.wishDraw;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;

@RequiredArgsConstructor
public class WishDrawRepositoryCustomImpl implements WishDrawRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<WishDrawSearchDto> findWishDrawPagingList(WishDrawSearchRequest request) {
        var pageable = request.getPageable();
        var content = queryFactory
                .select(Projections.fields(
                        WishDrawSearchDto.class,
                        wishDraw.id.as("wishDrawId"),
                        wishDraw.name.as("wishDrawName")
                ))
                .from(wishDraw)
                .where(wishSearchProvider(request))
                .orderBy(wishDraw.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return (Slice<WishDrawSearchDto>) PagingUtil.makeSlice(pageable, content);
    }

    public BooleanBuilder wishSearchProvider(WishDrawSearchRequest request){
        var booleanBuilder = new BooleanBuilder();
        if(anyNotNull(request.getMemberId())){
            booleanBuilder.and(wishDraw.member.id.eq(request.getMemberId()));
        }
        return booleanBuilder;
    }
}
