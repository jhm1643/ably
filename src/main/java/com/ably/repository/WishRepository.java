package com.ably.repository;

import com.ably.entity.Wish;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long> {

    Optional<Wish> findByIdAndMember_Id(Long id, Long memberId);

    @EntityGraph(attributePaths = {"product"})
    Slice<Wish> findAllByWishDraw_Id(Long id, Pageable pageable);
}
