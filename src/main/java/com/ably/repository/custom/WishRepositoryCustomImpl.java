package com.ably.repository.custom;

import com.ably.dto.common.pagination.type.PaginationType;
import com.ably.dto.wish.WishSearchDto;
import com.ably.dto.wish.request.WishSearchRequest;
import com.ably.util.PagingUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Slice;

import static com.ably.entity.QProduct.product;
import static com.ably.entity.QWish.wish;
import static org.apache.commons.lang3.ObjectUtils.anyNotNull;

@RequiredArgsConstructor
public class WishRepositoryCustomImpl implements WishRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    @Override
    public Slice<WishSearchDto> findWishPagingList(WishSearchRequest request) {
        var pageable = request.getPageable();
        var paginationType = request.getPaginationType();
        var limit = paginationType == PaginationType.SCROLL ? pageable.getPageSize() + 1 : pageable.getPageSize();
        var content = queryFactory
                .select(
                        Projections.fields(
                                WishSearchDto.class,
                                wish.id.as("wishId"),
                                product.id.as("productId"),
                                product.name.as("productName"),
                                product.thumbnail.as("productThumbnail"),
                                product.price.as("productPrice")
                        ))
                .from(wish)
                .leftJoin(wish.product, product)
                .where(wishConditionProvider(request))
                .orderBy(wish.id.desc())
                .offset(pageable.getOffset())
                .limit(limit)
                .fetch();

        return (Slice<WishSearchDto>) PagingUtil.makeSlice(paginationType, pageable, content, getCount(request));
    }

    private JPAQuery<Long> getCount(WishSearchRequest request){
        return queryFactory
                .select(wish.count())
                .from(wish)
                .where(wishConditionProvider(request));
    }

    public BooleanBuilder wishConditionProvider(WishSearchRequest request){
        var booleanBuilder = new BooleanBuilder();
        if(anyNotNull(request.getWishDrawId())){
            booleanBuilder.and(wish.wishDraw.id.eq(request.getWishDrawId()));
        }
        if(anyNotNull(request.getMemberId())){
            booleanBuilder.and(wish.member.id.eq(request.getMemberId()));
        }
        return booleanBuilder;
    }
}
