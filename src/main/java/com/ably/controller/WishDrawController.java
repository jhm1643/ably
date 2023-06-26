package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.wishdraw.request.WishDrawSaveRequest;
import com.ably.dto.wishdraw.request.WishDrawSearchRequest;
import com.ably.dto.wishdraw.response.WishDrawSearchResponse;
import com.ably.service.WishDrawService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("/ably/api/wish-draw")
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "WishDraw API")
public class WishDrawController {

    private final WishDrawService wishDrawService;

    @Operation(summary = "찜 서랍 목록 검색")
    @GetMapping
    public ResponseEntity<WishDrawSearchResponse> searchWishDraws(
            @AuthMemberId Long memberId,

            @ParameterObject
            @Valid WishDrawSearchRequest request
    ){
        request.setMemberId(memberId);
        return ResponseEntity.ok(wishDrawService.searchWishDraw(request));
    }

    @Operation(summary = "찜 서랍 생성",
            responses = @ApiResponse(responseCode = "400", description = "이미 존재하는 찜 서랍 이름"))
    @PostMapping
    public void saveWishDraw(
            @AuthMemberId Long memberId,
            @RequestBody @Valid WishDrawSaveRequest request
    ){
        request.setMemberId(memberId);
        wishDrawService.saveWishDraw(request);
    }

    @Operation(summary = "찜 서랍 삭제")
    @DeleteMapping("/{wishDrawId}")
    public void removeWishDraw(
            @AuthMemberId Long memberId,

            @Schema(description = "찜 서랍 ID")
            @PathVariable Long wishDrawId
    ){
        wishDrawService.removeWishDraw(memberId, wishDrawId);
    }
}
