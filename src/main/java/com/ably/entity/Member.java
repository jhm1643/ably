package com.ably.entity;

import com.ably.dto.user.request.SignUpRequest;
import com.ably.dto.user.type.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "MEMBER")
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MEMBER_SEQ")
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    @Column
    @Enumerated(value = EnumType.STRING)
    private RoleType role;

    @JsonIgnore
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WishDraw> wishDraws = new ArrayList<>();

    public static Member createUser(SignUpRequest request, RoleType roleType){
        return Member.builder()
                .email(request.getEmail())
                .password(request.getPassword())
                .role(roleType)
                .build();
    }
}
