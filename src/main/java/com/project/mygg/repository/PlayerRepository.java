package com.project.mygg.repository;

import com.project.mygg.entity.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<PlayerEntity,Long> {
    PlayerEntity findByNickName(String nickName);

    boolean existsByNickName(String nickName);
}
