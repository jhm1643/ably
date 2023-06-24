package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.wishdraw.request.WishDrawSaveRequest;
import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import com.ably.service.WishDrawService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/ably/api/wish-draw")
public class WishDrawController {

    private final WishDrawService wishDrawService;

    @GetMapping
    public ResponseEntity<WishDrawSearchResponse> searchWishDraws(
            @AuthMemberId Long memberId,
            @Valid WishDrawSearchRequest request
    ){
        return ResponseEntity.ok(wishDrawService.searchWishDraw(memberId, request));
    }

    @PostMapping
    public void saveWishDraw(
            @AuthMemberId Long memberId,
            @RequestBody @Valid WishDrawSaveRequest request
    ){
        wishDrawService.saveWishDraw(memberId, request);
    }

    @DeleteMapping("/{wishDrawId}")
    public void removeWishDraw(
            @AuthMemberId Long memberId,
            @PathVariable Long wishDrawId
    ){
        wishDrawService.removeWishDraw(memberId, wishDrawId);
    }
}
