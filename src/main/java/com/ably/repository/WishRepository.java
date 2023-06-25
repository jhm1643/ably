package com.ably.repository;

import com.ably.entity.Wish;
import com.ably.repository.custom.WishRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WishRepository extends JpaRepository<Wish, Long>, WishRepositoryCustom {

    Optional<Wish> findByIdAndMember_Id(Long id, Long memberId);
}
