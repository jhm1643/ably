package com.ably.security;

import com.ably.dto.user.response.JwtTokenResponse;
import com.ably.dto.user.type.RoleType;
import com.ably.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Collections;
import java.util.Date;

@Component
public class JwtProvider {

//    private static final long ACCESS_TOKEN_EXPIRE_TIME = 30 * 60 * 1000L;            // 30분
    private static final long ACCESS_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;         // 7일
    private static final long REFRESH_TOKEN_EXPIRE_TIME = 7 * 24 * 60 * 60 * 1000L;    // 7일
    private long tokenValidTime = 1000L * 60 * 60;

    private final Key key;
    public JwtProvider(@Value("${jwt.secret}") String secretKey){
        this.key = Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    public void tokenAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken, true);
        SecurityContextHolder.getContext().setAuthentication(getAuthentication(claims));
    }

    public JwtTokenResponse generateToken(Member member){
        var now = new Date().getTime();
        var accessToken = Jwts.builder()
                .claim("role", member.getRole().name())
                .claim("id", member.getId())
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();

        var refreshToken = Jwts.builder()
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key)
                .compact();

        return JwtTokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public Authentication getAuthentication(Claims claims) {
        Member member = Member.builder()
                .id(claims.get("id", Long.class))
                .password("")
                .role(RoleType.valueOf(claims.get("role", String.class)))
                .build();

        String role = claims.get("role", String.class);
        return new UsernamePasswordAuthenticationToken(new AuthUser(member), "", Collections.singletonList(new SimpleGrantedAuthority(role)));
    }

    public Claims parseClaims(String accessToken, Boolean isExpiredCheck) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            if(isExpiredCheck)
                throw e;
            else
                return e.getClaims();
        }
    }

    public Boolean isExpireToken(Claims claims) {
        return claims.getExpiration().before(new Date());
    }

    public String getExpireToken(String accessToken){
        return Jwts.builder().setClaims(parseClaims(accessToken, false).setExpiration(new Date())).compact();
    }
}

