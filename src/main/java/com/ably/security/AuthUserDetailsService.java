package com.ably.security;

import com.ably.exception.ApiError;
import com.ably.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return new AuthUser(memberRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(ApiError.NOT_FOUND_USER.getMessage())));
    }
}
