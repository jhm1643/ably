package com.ably.entity;

import com.ably.dto.product.request.ProductSaveRequest;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "PRODUCT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRODUCT_SEQ")
    private Long id;

    @Column
    private String name;

    @Column
    private String thumbnail;

    @Column
    private Long price;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Wish> wishes;

    public static Product createProduct(ProductSaveRequest request){
        return Product.builder()
                .name(request.getName())
                .thumbnail(request.getThumbnail())
                .price(request.getPrice())
                .build();
    }
}
