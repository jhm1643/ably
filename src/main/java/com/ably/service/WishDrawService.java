package com.ably.service;

import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import com.ably.dto.wishdraw.request.WishDrawSaveRequest;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import com.ably.entity.WishDraw;
import com.ably.mapper.wishdraw.WishDrawMapper;
import com.ably.repository.MemberRepository;
import com.ably.repository.WishDrawRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class WishDrawService {

    private final MemberRepository memberRepository;
    private final WishDrawRespository wishDrawRespository;
    private final WishDrawMapper wishDrawMapper;

    public void saveWishDraw(WishDrawSaveRequest request){
        memberRepository.findById(request.getMemberId())
                        .ifPresent(member -> member.addWishDraw(WishDraw.createWishDraw(request)));
    }

    public void removeWishDraw(Long memberId , Long wishDrawId){
        wishDrawRespository.findByIdAndMember_Id(wishDrawId, memberId)
                .ifPresent(wishDraw -> wishDrawRespository.delete(wishDraw));
    }

    @Transactional(readOnly = true)
    public WishDrawSearchResponse searchWishDraw(WishDrawSearchRequest request){
        return wishDrawMapper.toResponse(wishDrawRespository.findWishDrawPagingList(request));
    }
}
