package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.user.request.SignInRequest;
import com.ably.dto.user.request.SignUpRequest;
import com.ably.dto.user.response.JwtTokenResponse;
import com.ably.dto.user.response.MemberSearchResponse;
import com.ably.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ably/api/member")
@Tag(name = "Member API")
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "회원가입",
            responses = @ApiResponse(responseCode = "400", description = "이미 존재하는 이메일주소"))
    @PostMapping("/sign-up")
    public void signUp(
            @Valid @RequestBody SignUpRequest request
    ){
        memberService.signUp(request);
    }

    @Operation(summary = "로그인(토큰 발급)")
    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(
            @Valid @RequestBody SignInRequest request
    ){
        return ResponseEntity.ok(memberService.signIn(request));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "회원 탈퇴")
    @DeleteMapping("/withdrawal")
    public void withdrawal(
            @AuthMemberId Long memberId
    ) {
        memberService.withdrawal(memberId);
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "내 정보 검색")
    @GetMapping("/my-info")
    public ResponseEntity<MemberSearchResponse> searchUser(
            @AuthMemberId Long memberId
    ){
        return ResponseEntity.ok(memberService.searchMember(memberId));
    }
}
