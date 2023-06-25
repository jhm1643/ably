package com.ably.repository.custom;

import com.ably.dto.wish.WishSearchDto;
import com.ably.dto.wish.request.WishSearchRequest;
import com.ably.util.PagingUtil;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
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
                .innerJoin(wish.product, product)
                .where(wishSearchProvider(request))
                .orderBy(wish.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize() + 1)
                .fetch();

        return (Slice<WishSearchDto>) PagingUtil.makeSlice(pageable, content);
    }

//    public Long countWish(WishSearchRequest request){
//        return queryFactory
//                .select(wish.id)
//                .from(wish)
//                .innerJoin(product).on(product.id.eq(wish.product.id))
//                .where()
//    }

    public BooleanBuilder wishSearchProvider(WishSearchRequest request){
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
