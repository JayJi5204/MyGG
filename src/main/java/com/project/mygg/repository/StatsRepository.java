package com.project.mygg.repository;

import com.project.mygg.DTO.KdaDTO;
import com.project.mygg.DTO.StatDTO;
import com.project.mygg.entity.StatsEntity;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatsRepository extends JpaRepository<StatsEntity, Long> {

    Page<StatsEntity> findByNicknameContaining(String nickname, Pageable pageable);

    @Query("SELECT new com.project.mygg.DTO.KdaDTO(SUM(s.win),SUM(s.lose),SUM(s.kill),SUM(s.death),SUM(s.assist))" +
            "FROM StatsEntity s WHERE s.playerEntity.id= :playerId "
    )
    List<KdaDTO> findTotalKdaByPlayer(@Param("playerId") Long playerId);

    @Query("SELECT new com.project.mygg.DTO.StatDTO(s.championName,s.line,SUM(s.win),SUM(s.lose), SUM(s.kill), SUM(s.death), SUM(s.assist))" +
            "FROM StatsEntity s WHERE s.playerEntity.id= :playerId GROUP BY s.championName, s.line"
    )
    List<StatDTO> findStatDTOByPlayerId(@Param("playerId") Long playerId);


}
