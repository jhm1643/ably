package com.ably.service;

import com.ably.dto.wishdraw.request.WishDrawSaveRequest;
import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import com.ably.entity.WishDraw;
import com.ably.mapper.wishdraw.WishDrawMapper;
import com.ably.repository.WishDrawRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishDrawService {

    private final WishDrawRespository wishDrawRespository;
    private final WishDrawMapper wishDrawMapper;

    public void saveWishDraw(Long memberId, WishDrawSaveRequest request){
        wishDrawRespository.save(WishDraw.createWishDraw(memberId, request));
    }

    public void removeWishDraw(Long memberId , Long wishDrawId){
        wishDrawRespository.findByIdAndMember_Id(wishDrawId, memberId)
                .ifPresent(wishDraw -> wishDrawRespository.delete(wishDraw));
    }

    @Transactional(readOnly = true)
    public WishDrawSearchResponse searchWishDraw(Long memberId, WishDrawSearchRequest request){
        var pageable = PageRequest.of(request.getPage(), request.getLimit());
        var wishDrawSlice = wishDrawRespository.findAllByMember_Id(memberId, pageable);
        return wishDrawMapper.toResponse(wishDrawSlice);
    }
}
