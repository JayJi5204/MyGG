package com.project.mygg.repository;

import com.project.mygg.entity.PlayerEntity;
import com.project.mygg.enums.Tier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
    Optional<PlayerEntity> findByNickname(String nickname);

    boolean existsByNickname(String nickname);

    boolean existsByTier(Tier tier);

    List<PlayerEntity> findAllByOrderByTier();

}
