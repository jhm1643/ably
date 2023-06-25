package com.ably.entity;

import com.ably.dto.wishdraw.request.WishDrawSaveRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "WISH_DRAW", uniqueConstraints = @UniqueConstraint(name = "name_member_unique_01", columnNames = {"name", "member_id"}))
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class WishDraw {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISH_DRAW_SEQ")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Member member;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "wishDraw", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes;

    public static WishDraw createWishDraw(WishDrawSaveRequest request){
        return WishDraw.builder()
                .name(request.getWishDrawName())
                .build();
    }

    public void addWish(Wish wish){
        this.wishes.add(wish);
        wish.setWishDraw(this);
    }
}
