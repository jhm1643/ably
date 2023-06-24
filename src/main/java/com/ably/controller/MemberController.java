package com.ably.controller;

import com.ably.config.annotation.AuthMemberId;
import com.ably.dto.user.request.SignInRequest;
import com.ably.dto.user.request.SignUpRequest;
import com.ably.dto.user.response.JwtTokenResponse;
import com.ably.dto.user.response.MemberSearchResponse;
import com.ably.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ably/api/user")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/sign-up")
    public void signUp(
            @Valid @RequestBody SignUpRequest request
    ){
        memberService.signUp(request);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<JwtTokenResponse> signIn(
            @Valid @RequestBody SignInRequest request
    ){
        return ResponseEntity.ok(memberService.signIn(request));
    }

    @DeleteMapping("/withdrawal")
    public void withdrawal(
            @AuthMemberId Long memberId
    ) {
        memberService.withdrawal(memberId);
    }

    @GetMapping("/my-info")
    public ResponseEntity<MemberSearchResponse> searchUser(
            @AuthMemberId Long memberId
    ){
        return ResponseEntity.ok(memberService.searchMember(memberId));
    }
}
