package com.ably.service;

import com.ably.dto.wish.request.WishSearchRequest;
import com.ably.dto.wish.request.WishSaveRequest;
import com.ably.dto.wish.response.WishSearchResponse;
import com.ably.entity.Wish;
import com.ably.exception.ApiError;
import com.ably.exception.ApiException;
import com.ably.mapper.wish.WishMapper;
import com.ably.repository.ProductRepository;
import com.ably.repository.WishDrawRespository;
import com.ably.repository.WishRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishService {

    private final WishDrawRespository wishDrawRespository;
    private final ProductRepository productRepository;
    private final WishRepository wishRepository;

    private final WishMapper wishMapper;

    public void saveWish(WishSaveRequest request){
        var product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ApiException(ApiError.NOT_EXIST_DATA));

        wishDrawRespository.findByIdAndMember_Id(request.getWishDrawId(), request.getMemberId())
                .ifPresent(wishDraw -> wishDraw.addWish(Wish.createWish(product)));
    }

    public void removeWish(Long memberId, Long wishId){
        wishRepository.findByIdAndMember_Id(wishId, memberId)
                .ifPresent(wish -> wishRepository.delete(wish));
    }

    @Transactional(readOnly = true)
    public WishSearchResponse searchWish(WishSearchRequest request){
        var wishSlice = wishRepository.findWishPagingList(request);
        return wishMapper.toResponse(wishSlice);
    }
}
