package com.ably.service;

import com.ably.dto.user.request.SignInRequest;
import com.ably.dto.user.request.SignUpRequest;
import com.ably.dto.user.response.JwtTokenResponse;
import com.ably.dto.user.response.MemberSearchResponse;
import com.ably.dto.user.type.RoleType;
import com.ably.entity.Member;
import com.ably.exception.ApiError;
import com.ably.exception.ApiException;
import com.ably.mapper.member.MemberMapper;
import com.ably.repository.MemberRepository;
import com.ably.security.AuthUser;
import com.ably.security.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {

    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final MemberRepository memberRepository;

    private final MemberMapper memberMapper;

    public void signUp(SignUpRequest request) {
        if(memberRepository.existsByEmail(request.getEmail())) {
            throw new ApiException(ApiError.ALREADY_EXIST_DATA);
        }
        request.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));
        memberRepository.save(Member.createUser(request, RoleType.USER_ROLE));
    }

    public JwtTokenResponse signIn(SignInRequest request) {
        var authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        Member member = ((AuthUser) authentication.getPrincipal()).getMember();
        return jwtProvider.generateToken(member);
    }

    public void withdrawal(Long id){
        memberRepository.findById(id)
                .ifPresent(user -> memberRepository.delete(user));
    }

    public MemberSearchResponse searchMember(Long memberId){
        return memberRepository.findById(memberId)
                .map(memberMapper::toResponse)
                .orElseThrow(()->new ApiException(ApiError.NOT_EXIST_DATA));
    }
}
