package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.wish.request.WishSaveRequest;
import com.ably.dto.wish.request.WishSearchRequest;
import com.ably.dto.wish.response.WishSearchResponse;
import com.ably.service.WishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/ably/api/wish")
public class WishController {

    private final WishService wishService;

    @PostMapping
    public void saveWish(
            @AuthMemberId Long memberId,
            @RequestBody @Valid WishSaveRequest request
    ) {
        wishService.saveWish(memberId, request);
    }

    @GetMapping
    public ResponseEntity<WishSearchResponse> searchWish(
            WishSearchRequest request
    ){
        return ResponseEntity.ok(wishService.searchWish(request));
    }

    @DeleteMapping("/{wishId}")
    public void removeWish(
            @AuthMemberId Long memberId,
            @PathVariable Long wishId
    ){
        wishService.removeWish(memberId, wishId);
    }
}
