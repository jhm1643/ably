package com.ably.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
@Table(name = "WISH", uniqueConstraints = {@UniqueConstraint(columnNames = {"member_id", "product_id"})})
public class Wish {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "WISH_SEQ")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @Setter
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    private WishDraw wishDraw;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;

    public static Wish createWish(Product product){
        return Wish.builder()
                .product(product)
                .build();
    }

    public void setWishDraw(WishDraw wishDraw) {
        this.wishDraw = wishDraw;
        this.member = wishDraw.getMember();
    }
}
