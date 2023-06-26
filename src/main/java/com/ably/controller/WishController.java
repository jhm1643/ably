package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.wish.request.WishSearchRequest;
import com.ably.dto.wish.request.WishSaveRequest;
import com.ably.dto.wish.response.WishSearchResponse;
import com.ably.service.WishService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/ably/api/wish")
@Tag(name = "Wish API")
public class WishController {

    private final WishService wishService;

    @Operation(summary = "찜 추가")
    @PostMapping
    public void saveWish(
            @AuthMemberId Long memberId,
            @RequestBody @Valid WishSaveRequest request
    ) {
        request.setMemberId(memberId);
        wishService.saveWish(request);
    }

    @Operation(summary = "찜 서랍의 찜 목록 검색")
    @GetMapping
    public ResponseEntity<WishSearchResponse> searchWish(
            @AuthMemberId Long memberId,
            @ParameterObject WishSearchRequest request
    ){
        request.setMemberId(memberId);
        return ResponseEntity.ok(wishService.searchWish(request));
    }

    @Operation(summary = "찜 해제")
    @DeleteMapping("/{wishId}")
    public void removeWish(
            @AuthMemberId Long memberId,
            @Schema(description = "찜 ID") @PathVariable Long wishId
    ){
        wishService.removeWish(memberId, wishId);
    }
}
