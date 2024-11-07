package com.project.mygg.repository;

import com.project.mygg.entity.ChampionStatsEntity;
import com.project.mygg.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChampionStatsRepository extends JpaRepository<ChampionStatsEntity,Long> {
}
