package com.ably.repository;

import com.ably.entity.WishDraw;
import com.ably.repository.custom.WishDrawRepositoryCustom;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishDrawRespository extends JpaRepository<WishDraw, Long>, WishDrawRepositoryCustom {

    Optional<WishDraw> findByIdAndMember_Id(Long id, Long memberId);
    Slice<WishDraw> findAllByMember_Id(Long memberId, Pageable pageable);
}
