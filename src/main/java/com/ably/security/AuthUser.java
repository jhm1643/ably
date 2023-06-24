package com.ably.security;

import com.ably.entity.Member;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collections;

@Getter
public class AuthUser extends User {

    private Member member;

    public AuthUser(Member member){
        super(
                String.valueOf(member.getId()),
                member.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(member.getRole().name()))
        );
        this.member = member;
    }
}
